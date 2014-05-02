package DAL;

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

    private DALAppearance() throws SQLServerException, SQLException {
        m_connection = DBConnection.getInstance().getConnection();
        dalFireman = DALFireman.getInstance();
        dalVehicle = DALVehicle.getInstance();
        dalTime = DALTimelist.getInstance();

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
        if (veh == null) {
            ps.setString(1, null);
        } else {
            ps.setInt(1, veh.getOdinnummer());
        }
        ps.setInt(2, tm.getId());
        ps.setInt(3, 0);
        ps.setInt(4, total);
        ps.setInt(5, -1);
        ps.setBoolean(6, false);
        ps.setBoolean(7, hl);
        ps.setBoolean(8, false);
        ps.setBoolean(9, ch);
        ps.setBoolean(10, st);
        ps.execute();
    }

    public void populateAppearances() throws SQLException {

        String sql = "SELECT * FROM Fremmøde WHERE hlGodkendt = 'false'";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();
        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            int køtjRef = result.getInt("køtjRef");
            int tidsregistreringRef = result.getInt("tidsregistreringRef");
            int totalTid = result.getInt("totaltid");
            int evaNo = result.getInt("evaNo");
            int type = result.getInt("kørselType");
            boolean hlGodkendt = result.getBoolean("hlGodkendt");
            boolean ilGodkendt = result.getBoolean("ilGodkendt");
            boolean holdleder = result.getBoolean("holdleder");
            boolean chauffør = result.getBoolean("chauffør");
            boolean stationsvagt = result.getBoolean("stationsvagt");

            BEVehicle localVehicle = null;
            for (BEVehicle veh : dalVehicle.getVehicles()) {
                if (veh.getOdinnummer() == køtjRef) {
                    localVehicle = veh;
                }
            }

            BETime localTime = null;
            for (BETime time : dalTime.getAllTimes()) {
                if (time.getId() == tidsregistreringRef) {
                    localTime = time;
                }
            }
            BEAppearance appearance = new BEAppearance(id, localVehicle, localTime, totalTid, evaNo, hlGodkendt, ilGodkendt, holdleder, chauffør, stationsvagt, type);
            allAppearances.add(appearance);
        }
    }

    public ArrayList<BEAppearance> getAppearances() {
        return allAppearances;
    }

    public void confirmTeam(BEAppearance appearance) throws SQLException {
            String sql = "UPDATE Fremmøde SET hlGodkendt = ?, type = ? WHERE id = ?";

            PreparedStatement ps = m_connection.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setInt(2, appearance.getType());
            ps.setInt(3, appearance.getId());
            appearance.setHlGodkendt(true);
            ps.execute();
    }
}
