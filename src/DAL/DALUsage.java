package DAL;

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
    private ArrayList<BEMateriel> materiel = new ArrayList<>();
    private ArrayList<BEUsage> allUsages = new ArrayList<BEUsage>();

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

        PreparedStatement ps = getM_connection().prepareStatement(sql);
        ps.setInt(1, u.getAlarm().getId());
        ps.setInt(2, u.getMateriel().getId());
        ps.setInt(3, u.getAmount());
        ps.execute();
        allUsages.add(u);
    }

    public void populateMats() throws SQLException {
        String sql = "SELECT * FROM Brandmateriel";

        PreparedStatement ps = getM_connection().prepareStatement(sql);
        ps.execute();

        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("navn");

            BEMateriel mat = new BEMateriel(id, name);
            materiel.add(mat);
        }
    }
    public ArrayList<BEUsage> getAllUsages(){
        return allUsages;
    }

    /**
     * @return the m_connection
     */
    public Connection getM_connection() {
        return m_connection;
    }

    /**
     * @param m_connection the m_connection to set
     */
    public void setM_connection(Connection m_connection) {
        this.m_connection = m_connection;
    }

    /**
     * @return the materiel
     */
    public ArrayList<BEMateriel> getMateriel() {
        return materiel;
    }

    /**
     * @param materiel the materiel to set
     */
    public void setMateriel(ArrayList<BEMateriel> materiel) {
        this.materiel = materiel;
    }
}
