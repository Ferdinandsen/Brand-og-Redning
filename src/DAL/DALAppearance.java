package DAL;

import BE.BEAlarm;
import BE.BEAppearance;
import BE.BETime;
import BE.BEVehicle;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private DALTimelist dalTime;
    private DALFireman dalFireman;
    DALALarm dalAlarm;

    private DALAppearance() throws SQLServerException, SQLException {
        m_connection = DBConnection.getInstance().getConnection();
        dalFireman = DALFireman.getInstance();
        dalVehicle = DALVehicle.getInstance();
        dalTime = DALTimelist.getInstance();
        dalAlarm = DALALarm.getInstance();

        populateAppearances();
    }

    public static DALAppearance getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALAppearance();
        }
        return m_instance;
    }

    public void endShift(BETime tm, BEVehicle veh, boolean hl, boolean ch, boolean st, int total) throws SQLException {
        String sql = "INSERT INTO Fremmøde VALUES (?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, tm.getId());
        ps.setInt(2, total);
        ps.setBoolean(3, false);
        ps.setBoolean(4, hl);
        ps.setBoolean(5, ch);
        ps.setBoolean(6, st);
        ps.setBoolean(7, false);
        ps.setInt(8, 0);
        ps.setString(9, null);
        if (veh == null) {
            ps.setString(10, null);
        } else {
            ps.setInt(10, veh.getOdinnummer());
        }
        ps.execute();
    }

    public void populateAppearances() throws SQLException {

        String sql = "SELECT * FROM Fremmøde WHERE hlGodkendt = 'false'";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();
        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            int tidsregistreringRef = result.getInt("tidsregistreringRef");
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
                if (alarm.getEvaNo() == alarmRef) {
                    localAlarm = alarm;
                }
            }
            BETime localTime = null;
            for (BETime time : dalTime.getAllTimes()) {
                if (time.getId() == tidsregistreringRef) {
                    localTime = time;
                }
            }
            BEVehicle localVeh = null;
            for (BEVehicle veh : dalVehicle.getVehicles()) {
                if (veh.getOdinnummer() == vehRef) {
                    localVeh = veh;
                }
            }

            BEAppearance appearance = new BEAppearance(id, localTime, totalTid, hlGodkendt, ilGodkendt, holdleder, chauffør, stationsvagt, type, localAlarm, localVeh);
            allAppearances.add(appearance);
        }
    }

    public ArrayList<BEAppearance> getAppearances() {
        return allAppearances;
    }

    public void confirmTeam(BEAppearance appearance) throws SQLException {
        String sql = "UPDATE Fremmøde SET hlGodkendt = ?, kørselType = ?, alarmRef = ? WHERE id = ?";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setBoolean(1, true);
        ps.setInt(2, appearance.getType());
        ps.setInt(3, appearance.getAlarm().getEvaNo());
        ps.setInt(4, appearance.getId());
        appearance.setHlGodkendt(true);
        ps.execute();
    }
}
