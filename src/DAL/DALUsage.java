package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Team Kawabunga
 */
public class DALUsage {

    private Connection m_connection;
    private static DALUsage m_instance = null;

    private DALUsage() throws SQLServerException, SQLException {
        m_connection = DBConnection.getInstance().getConnection();
    }

    public static DALUsage getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALUsage();
        }
        return m_instance;
    }
}
