package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author Shadowleet
 */

public class DBConnection {

    private final Connection m_connection;
    private static DAL.DBConnection m_instance = null;
    
    private static final String SERVER_NAME = "localhost";
    private static final String DATABASE_INSTANCE = "SQLEXPRESS";
    private static final int PORTNO = 58828; //1433; //49197;
    private static final String DATABASE_NAME = "Brand og Redning";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "mobiler123";

    private DBConnection() throws SQLServerException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName(SERVER_NAME);
        ds.setInstanceName(DATABASE_INSTANCE);
        ds.setDatabaseName(DATABASE_NAME);
        ds.setPortNumber(PORTNO);
        ds.setUser(USERNAME);
        ds.setPassword(PASSWORD);
        m_connection = ds.getConnection();
    }

    public Connection getConnection() {
        return m_connection;
    }

    public static DAL.DBConnection getInstance() throws SQLServerException {
        if (m_instance == null) {
            m_instance = new DAL.DBConnection();
        }
        return m_instance;
    }
}


