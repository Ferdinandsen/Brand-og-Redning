package DAL;

import BE.BEAlarm;
import BE.BEMateriel;
import BE.BEUsage;
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
public class DALUsage {

    private Connection m_connection;
    private static DALUsage m_instance = null;
    private ArrayList<BEMateriel> allMaterials;
    private ArrayList<BEUsage> allUsages;
    private DALALarm dalAlarm;

    private DALUsage() throws SQLServerException, SQLException {
        m_connection = DBConnection.getInstance().getConnection();
        dalAlarm = DALALarm.getInstance();
        populateMats();
        populateUsages();

    }

    public static DALUsage getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALUsage();
        }
        return m_instance;
    }

    /**
     * Creates a new usage report and adds its ID to it. Adds it to ArrayList
     * allUsages
     *
     * @param u -BEUsage
     * @throws SQLException
     */
    public void createUsageReport(BEUsage u) throws SQLException {
        String sql = "INSERT INTO Forbrug VALUES (?,?,?) select @@identity";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, u.getMateriel().getId());
        ps.setInt(2, u.getAmount());
        ps.setInt(3, u.getAlarm().getId());
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        int id = 0;
        if (rs.next()) {
            id = rs.getInt(1);
        }
        u.setId(id);
        allUsages.add(u);
    }

    /**
     * populate the ArrayList allUsages
     *
     * @throws SQLException
     */
    public void populateUsages() throws SQLException {
        allUsages = new ArrayList<>();
        String sql = "SELECT * FROM Forbrug";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();

        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            int matId = result.getInt("matId");
            int amount = result.getInt("amount");
            int alarmRef = result.getInt("alarmRef");

            BEMateriel localMateriel = null;
            for (BEMateriel mat : getAllMats()) {
                if (mat.getId() == matId) {
                    localMateriel = mat;
                }
            }

            BEAlarm localAlarm = null;
            for (BEAlarm alarm : dalAlarm.getAllAlarms()) {
                if (alarm.getId() == alarmRef) {
                    localAlarm = alarm;
                }
            }

            BEUsage usage = new BEUsage(id, localMateriel, amount, localAlarm);
            allUsages.add(usage);
        }
    }

    /**
     * Populate the ArrayList allMaterials
     *
     * @throws SQLException
     */
    public void populateMats() throws SQLException {
        allMaterials = new ArrayList<>();
        String sql = "SELECT * FROM Brandmateriel";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();

        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("navn");

            BEMateriel mat = new BEMateriel(id, name);
            allMaterials.add(mat);
        }
    }

    /**
     * Updates a usage report
     *
     * @param bu - BEUsage
     * @throws SQLException
     */
    public void updateUsageReport(BEUsage bu) throws SQLException {
        String sql = "UPDATE Forbrug SET amount = ? WHERE id = ?";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, bu.getAmount());
        ps.setInt(2, bu.getId());
        ps.execute();
    }

    /**
     * removes a material from the ArrayList and database
     *
     * @param mat - BEMateriel
     * @throws SQLException
     */
    public void deleteMaterial(BEMateriel mat) throws SQLException {
        String sql = "DELETE FROM Brandmateriel WHERE id = ?";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, mat.getId());
        ps.execute();
        allMaterials.remove(mat);
    }

    /**
     * creates a new material in the database and adds it to the ArrayList
     * allMaterials
     *
     * @param text
     * @throws SQLException
     */
    public void addMaterial(String text) throws SQLException {
        String sql = "INSERT INTO Brandmateriel VALUES (?)  select @@identity";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setString(1, text);
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        int id = 0;
        if (rs.next()) {
            id = rs.getInt(1);

            BEMateriel mat = new BEMateriel(id, text);
            allMaterials.add(mat);
        }
    }

    /**
     * returns the ArrayList
     *
     * @return allUsages
     */
    public ArrayList<BEUsage> getAllUsages() {
        return allUsages;
    }

    /**
     * returns the ArrayList
     *
     * @return the allMateriel
     */
    public ArrayList<BEMateriel> getAllMats() {
        return allMaterials;
    }
}
