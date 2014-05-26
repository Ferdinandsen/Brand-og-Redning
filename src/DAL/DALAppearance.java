package DAL;

import BE.BEAlarm;
import BE.BEAppearance;
import BE.BEFireman;
import BE.BELogin;
import BE.BEVehicle;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Team Kawabunga
 */
public class DALAppearance {

    private Connection m_connection;
    private static DALAppearance m_instance = null;
    private ArrayList<BEAppearance> allAppearances;
    private DALVehicle dalVehicle;
    private DALFireman dalFireman;
    private DALLogin dALLogin;
    DALALarm dalAlarm;

    private DALAppearance() throws SQLServerException, SQLException {
        m_connection = DBConnection.getInstance().getConnection();
        dalFireman = DALFireman.getInstance();
        dalVehicle = DALVehicle.getInstance();
        dALLogin = DALLogin.getInstance();
        dalAlarm = DALALarm.getInstance();

        populateAppearances();
    }

    public static DALAppearance getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALAppearance();
        }
        return m_instance;
    }

    /**
     * Creates the Appearance in the database
     *
     * @param alarm BEAlarm
     * @param fireman BEFireman
     * @param veh BEVehicle
     * @param hl boolean
     * @param ch boolean
     * @param st boolean
     * @param total calculated total time
     * @param time Timestamp
     * @throws SQLException
     */
    public void endShift(BEAlarm alarm, BEFireman fireman, BEVehicle veh, boolean hl, boolean ch, boolean st, int total, Timestamp time) throws SQLException {
        String sql = "INSERT INTO Fremmøde VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, fireman.getMedarbjeder().getMedarbejderNo());
        ps.setInt(2, total);
        ps.setTimestamp(3, alarm.getTime());
        ps.setTimestamp(4, time);
        ps.setBoolean(5, false);
        ps.setBoolean(6, hl);
        ps.setBoolean(7, ch);
        ps.setBoolean(8, st);
        ps.setBoolean(9, false);
        ps.setString(10, null);
        ps.setInt(11, alarm.getId());

        if (veh == null) {
            ps.setString(12, null);
        } else {
            ps.setInt(12, veh.getOdinnummer());
        }
        ps.setString(13, null);
        ps.setBoolean(14, false);
        ps.setTimestamp(15, null);
        ps.execute();
    }

    /**
     * Populates the allAppearances ArrayList
     *
     * @throws SQLException
     */
    public void populateAppearances() throws SQLException {
        allAppearances = new ArrayList<>();
        String sql = "SELECT * FROM Fremmøde";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();
        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            int deltidsbrandmandRef = result.getInt("deltidsbrandmandRef");
            Timestamp checkIn = result.getTimestamp("checkInTime");
            Timestamp checkOut = result.getTimestamp("checkOutTime");
            int totalTid = result.getInt("totaltid");
            int type = result.getInt("kørselType");
            boolean hlGodkendt = result.getBoolean("hlGodkendt");
            boolean ilGodkendt = result.getBoolean("ilGodkendt");
            boolean holdleder = result.getBoolean("holdleder");
            boolean chauffør = result.getBoolean("chauffør");
            boolean stationsvagt = result.getBoolean("stationsvagt");
            int alarmRef = result.getInt("alarmRef");
            int vehRef = result.getInt("køtjRef");
            int loginRef = result.getInt("loginRef");
            boolean lønDone = result.getBoolean("lønDone");
            Timestamp lønTime = result.getTimestamp("lønTime");

            BEAlarm localAlarm = null;
            for (BEAlarm alarm : dalAlarm.getAllAlarms()) {
                if (alarm.getId() == alarmRef) {
                    localAlarm = alarm;
                }
            }
            BEFireman localFireman = null;
            for (BEFireman fireman : dalFireman.getAllFiremen()) {
                if (fireman.getMedarbjeder().getMedarbejderNo() == deltidsbrandmandRef) {
                    localFireman = fireman;
                }
            }
            BEVehicle localVeh = null;
            for (BEVehicle veh : dalVehicle.getVehicles()) {
                if (veh.getOdinnummer() == vehRef) {
                    localVeh = veh;
                }
            }

            BELogin localLogin = null;
            for (BELogin login : dALLogin.getAllLogins()) {
                if (login.getMedarbejder().getMedarbejderNo() == loginRef) {
                    localLogin = login;
                }
            }
            BEAppearance appearance = new BEAppearance(id, localFireman, checkIn, checkOut, totalTid, hlGodkendt, ilGodkendt,
                    holdleder, chauffør, stationsvagt, type, localAlarm, localVeh, localLogin, lønDone, lønTime);
            if (appearance.getFireman().getMedarbjeder().getFornavn().equalsIgnoreCase("gæst")) {
                appearance.getAlarm().setGuestConfirmed(true);
            }
            allAppearances.add(appearance);
        }
    }

    /**
     * Retrieve the ArrayList
     *
     * @return ArrayList of allAppearances
     */
    public ArrayList<BEAppearance> getAppearances() {
        return allAppearances;
    }

    /**
     * Updates the appearance, mainly setting the hlgodkendt bit.
     *
     * @param appearance - BEAppearance
     * @throws SQLException
     */
    public void confirmTeam(BEAppearance appearance) throws SQLException {
        String sql = "UPDATE Fremmøde SET hlGodkendt = ?, kørselType = ?, alarmRef = ?, loginRef = ? WHERE id = ?";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setBoolean(1, true);
        ps.setInt(2, appearance.getType());
        ps.setInt(3, appearance.getAlarm().getId());
        ps.setInt(4, appearance.getLogin().getMedarbejder().getMedarbejderNo());
        ps.setInt(5, appearance.getId());
        appearance.setHlGodkendt(true);
        ps.execute();
    }

    /**
     * Updates the kørselType in database
     *
     * @param a - BEAppearance
     * @throws SQLException
     */
    public void updateKørselType(BEAppearance a) throws SQLException {
        String sql = "UPDATE Fremmøde SET kørselType = ? WHERE id = ?";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, a.getKørselsType());
        ps.setInt(2, a.getId());
        ps.execute();
    }

    /**
     * Updates the time in the database for the appearance
     *
     * @param appearance BEAppearance
     * @throws SQLException
     */
    public void updateAppearance(BEAppearance appearance) throws SQLException {
        String sql = "UPDATE Fremmøde SET checkInTime = ?, checkOutTime = ?, totaltid = ? WHERE id = ?";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setTimestamp(1, appearance.getCheckIn());
        ps.setTimestamp(2, appearance.getCheckOut());
        ps.setInt(3, appearance.getTotalTid());
        ps.setInt(4, appearance.getId());
        ps.execute();
    }

    /**
     * delete the specific ID
     *
     * @param appearance
     * @throws SQLException
     */
    public void deleteAppearance(BEAppearance appearance) throws SQLException {
        String sql = "DELETE FROM Fremmøde WHERE id = ?";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, appearance.getId());
        ps.execute();
        allAppearances.remove(appearance);
    }

    /**
     * Creates an appearance in the database - this one is used from IL
     *
     * @param fireman
     * @param total
     * @param time
     * @param alarm
     * @param veh
     * @param checkOutTime
     * @param hl
     * @param ch
     * @param st
     * @param kørselstype
     * @throws SQLException
     */
    public void createAppearance(BEFireman fireman, int total, Timestamp time, BEAlarm alarm, BEVehicle veh, String checkOutTime, boolean hl, boolean ch, boolean st, int kørselstype) throws SQLException {
        String sql = "INSERT INTO Fremmøde VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) select @@identity";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, fireman.getMedarbjeder().getMedarbejderNo());
        ps.setInt(2, total);
        ps.setTimestamp(3, alarm.getTime());
        ps.setTimestamp(4, time);
        ps.setBoolean(5, true);
        ps.setBoolean(6, hl);
        ps.setBoolean(7, ch);
        ps.setBoolean(8, st);
        ps.setBoolean(9, false);
        ps.setInt(10, kørselstype);
        ps.setInt(11, alarm.getId());

        if (veh == null) {
            ps.setString(12, null);
        } else {
            ps.setInt(12, veh.getOdinnummer());
        }
        ps.setString(13, null);
        ps.setBoolean(14, false);
        ps.setTimestamp(15, null);
        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        int id = 0;
        if (rs.next()) {
            id = rs.getInt(1);
            BEAppearance appearance = new BEAppearance(id, fireman, alarm.getTime(), time, total, true, false, hl, ch, st, kørselstype, alarm, veh, null, false, null);
            allAppearances.add(appearance);
        }
    }

    /**
     * Updates the appearance in the database - when IL has confirmed the
     * action.
     *
     * @param appearance
     * @throws SQLException
     */
    public void confirmAlarmTeam(BEAppearance appearance) throws SQLException {
        String sql = "UPDATE Fremmøde SET ilGodkendt = ? WHERE id = ?";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setBoolean(1, true);
        ps.setInt(2, appearance.getId());
        ps.execute();
    }

    /**
     * Updates the bit holdleder on appearance in database with the specific ID
     *
     * @param appearance
     * @throws SQLException
     */
    public void updateFunction(BEAppearance appearance) throws SQLException {
        String sql = "UPDATE Fremmøde SET holdleder = ? WHERE id = ?";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setBoolean(1, appearance.isHoldleder());
        ps.setInt(2, appearance.getId());
        ps.execute();
    }

    /**
     * Updates Fremmøde with salary information - on specific ID
     *
     * @param a BEAppearance
     * @param ts Timestamp
     * @throws SQLException
     */
    public void createSalary(BEAppearance a, Timestamp ts) throws SQLException {
        String sql = "UPDATE Fremmøde SET lønDone = ?, lønTime =? where id = ?";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setBoolean(1, true);
        ps.setTimestamp(2, ts);
        ps.setInt(3, a.getId());
        ps.execute();
    }
}
