package DAL;

import BE.BEAlarm;
import BE.BEAppearance;
import BE.BEEmployee;
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
    private ArrayList<BEAppearance> allAppearances = new ArrayList<>();
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

    public void endShift(BEAlarm alarm, BEFireman fireman, BEVehicle veh, boolean hl, boolean ch, boolean st, int total, Timestamp time) throws SQLException {
        String sql = "INSERT INTO Fremmøde VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
        ps.setString(14, null);
        ps.setString(15, null);
        ps.setString(16, null);
        ps.setString(17, null);

        ps.execute();
    }

    public void populateAppearances() throws SQLException {
        allAppearances = new ArrayList<>();
        String sql = "SELECT * FROM Fremmøde WHERE hlGodkendt = 'false'";

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
            String hlBemærkning = result.getString("hlBemærkning");
            String ilBemærkning = result.getString("ilBemærkning");
            Timestamp ilGodkendtTid = result.getTimestamp("ilGodkendtTid");
            Timestamp hlGodkendtTid = result.getTimestamp("hlGodkendtTid");

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
            BEAppearance appearance
                    = new BEAppearance(id, localFireman, checkIn, checkOut, totalTid, hlGodkendt, ilGodkendt,
                            holdleder, chauffør, stationsvagt, type, localAlarm, localVeh, localLogin,
                            hlBemærkning, ilBemærkning, ilGodkendtTid, hlGodkendtTid);
            allAppearances.add(appearance);
        }
    }

    public ArrayList<BEAppearance> getAppearances() {
        return allAppearances;
    }

    public void confirmTeam(BEAppearance appearance, String comment) throws SQLException {
        String sql = "UPDATE Fremmøde SET hlGodkendt = ?, kørselType = ?, alarmRef = ?, hlBemærkning = ? WHERE id = ?";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setBoolean(1, true);
        ps.setInt(2, appearance.getType());
        ps.setInt(3, appearance.getAlarm().getId());
        ps.setString(4, comment);
        ps.setInt(5, appearance.getId());
        appearance.setHlGodkendt(true);
        ps.execute();
    }

    public void updateTime(BEAppearance appearance, int total) throws SQLException {

        String sql = "UPDATE Fremmøde SET checkInTime = ?, checkOutTime = ?, totaltid = ? WHERE id = ?";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setTimestamp(1, appearance.getCheckIn());
        ps.setTimestamp(2, appearance.getCheckOut());
        ps.setInt(3, total);
        ps.setInt(4, appearance.getId());
        ps.execute();
    }
}
