package DAL;

import BE.BEAlarm;
import BE.BEMateriel;
import BE.BEUsage;
import BLL.BLLAlarm;
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
    private ArrayList<BEMateriel> allMaterials = new ArrayList<>();
    private ArrayList<BEUsage> allUsages = new ArrayList<BEUsage>();
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

    public void populateUsages() throws SQLException {
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

    public void populateMats() throws SQLException {
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

    public ArrayList<BEUsage> getAllUsages() {
        return allUsages;
    }

    /**
     * @return the materiel
     */
    public ArrayList<BEMateriel> getAllMats() {
        return allMaterials;
    }
}
