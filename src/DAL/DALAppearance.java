package DAL;

import BE.BEAlarm;
import BE.BEAppearance;
import BE.BEFireman;
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
    DALALarm dalAlarm;

    private DALAppearance() throws SQLServerException, SQLException {
        m_connection = DBConnection.getInstance().getConnection();
        dalFireman = DALFireman.getInstance();
        dalVehicle = DALVehicle.getInstance();
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
        String sql = "INSERT INTO Fremmøde VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, fireman.getMedarbjeder().getMedarbejderNo());
        ps.setInt(2, total);
        ps.setTimestamp(3, time);
        ps.setBoolean(4, false);
        ps.setBoolean(5, hl);
        ps.setBoolean(6, ch);
        ps.setBoolean(7, st);
        ps.setBoolean(8, false);
        ps.setString(9, null);
        ps.setInt(10, alarm.getId());
        if (veh == null) {
            ps.setString(11, null);
        } else {
            ps.setInt(11, veh.getOdinnummer());
        }
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

            BEAppearance appearance = new BEAppearance(id, localFireman, checkOut, totalTid, hlGodkendt, ilGodkendt, holdleder, chauffør, stationsvagt, type, localAlarm, localVeh);
            allAppearances.add(appearance);
        }
    }

    public ArrayList<BEAppearance> getAppearances() {
        return allAppearances;
    }

    public void confirmTeam(BEAppearance appearance) throws SQLException {
        String sql = "UPDATE Fremmøde SET hlGodkendt = ?, kørselType = ?, alarmRef = ? WHERE id = ?";
        System.out.println("dal");
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setBoolean(1, true);
        ps.setInt(2, appearance.getType());
            ps.setInt(3, appearance.getAlarm().getId());
        ps.setInt(4, appearance.getId());
        appearance.setHlGodkendt(true);
        ps.execute();
    }
}
