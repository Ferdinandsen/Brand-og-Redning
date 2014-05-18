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
    DALALarm dalAlarm;

    private DALUsage() throws SQLServerException, SQLException {
        m_connection = DBConnection.getInstance().getConnection();
        
        populateMats();
        
    }

    public static DALUsage getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALUsage();
        }
        return m_instance;
    }

    public void createUsageReport(BEUsage u) throws SQLException {
        String sql = "INSERT INTO Forbrug VALUES (?,?,?)";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, u.getAlarm().getId());
        ps.setInt(2, u.getMateriel().getId());
        ps.setInt(3, u.getAmount());
        ps.execute();
        allUsages.add(u);
    }

    public void populateUsages() throws SQLException {
        String sql = "SELECT * FROM Forbrug";
        dalAlarm = DALALarm.getInstance();
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();

        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            int alarmRef = result.getInt("alarmRef");
            int matId = result.getInt("matId");
            int amount = result.getInt("amount");

            BEAlarm localAlarm = null;
            
            for (BEAlarm alarm : dalAlarm.getAllAlarms()) {
                if (alarm.getId() == alarmRef) {
                    localAlarm = alarm;
                }
            }
            
            BEMateriel localMateriel = null;
            for (BEMateriel mat : getAllMats()) {
                if (mat.getId() == matId) {
                    localMateriel = mat;
                }
            }


            BEUsage usage = new BEUsage(id, localAlarm, localMateriel, amount);
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
