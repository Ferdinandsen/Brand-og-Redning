package DAL;

import BE.BEError;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Shadowleet
 */
public class DALReport {

    private Connection m_connection;
    private static DALReport m_instance;

    private DALReport() throws SQLException, SQLServerException {
        m_connection = DBConnection.getInstance().getConnection();

        // populateDalReport();
    }

    public static DALReport getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALReport();
        }
        return m_instance;
    }

    public void createErrorReport(BEError be) throws SQLException{
        System.out.println("Error: " + be.getError() + "Course " + be.getCourse()); 
    }

}
