package DAL;

import BE.BETime;
import BE.BEVehicle;
import BE.BETime;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Jacob
 */
public class DALTimelist {

    private Connection m_connection;
    private static DALTimelist m_instance = null;

    private DALTimelist() throws SQLServerException, SQLException {
        m_connection = DBConnection.getInstance().getConnection();
    }

    public static DALTimelist getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALTimelist();
        }
        return m_instance;
    }

    public void updateTime(BETime time, BEVehicle odin, boolean hl, boolean ch, boolean st) {
        
    }
}
