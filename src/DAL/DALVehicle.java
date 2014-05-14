package DAL;

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
public class DALVehicle {

    private Connection m_connection;
    private static DALVehicle m_instance = null;
    private ArrayList<BEVehicle> vehicles = new ArrayList<>();

    private DALVehicle() throws SQLException, SQLServerException {
        m_connection = DBConnection.getInstance().getConnection();
        populateVehicle();
    }

    public static DALVehicle getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALVehicle();
        }
        return m_instance;
    }

    public void populateVehicle() throws SQLException {
        vehicles = new ArrayList<>();
        String sql = "SELECT * FROM KØTJ ORDER by odinNo";

        PreparedStatement ps = getM_connection().prepareStatement(sql);
        ps.execute();

        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int odin = result.getInt("odinNo");
            String reg = result.getString("registrering");
            String mark = result.getString("mærke");
            String mod = result.getString("model");
            String desc = result.getString("beskrivelse");

            BEVehicle bil = new BEVehicle(odin, reg, mark, mod, desc);
            vehicles.add(bil);
        }
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
     * @return the vehicles
     */
    public ArrayList<BEVehicle> getVehicles() {
        return vehicles;
    }

    /**
     * @param vehicles the vehicles to set
     */
    public void setVehicles(ArrayList<BEVehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
