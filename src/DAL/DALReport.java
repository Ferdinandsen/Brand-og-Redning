package DAL;

import BE.BEAppearance;
import BE.BEError;
import BE.BEVehicle;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Team Kawabunga
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
    
    public void createErrorReport(BEError be, BEVehicle veh) throws SQLException {
        String sql = "INSERT INTO Report VALUES (?,?,?,?,?,?,?,?)";
        
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, veh.getOdinnummer());
        ps.setString(2, be.getError());
        ps.setString(3, be.getCourse());
        ps.setString(4, be.getOutoforder());
        ps.setString(5, be.getUrgent());
        ps.setString(6, be.getInduetime());
        ps.setString(7, be.getWash());
        ps.setBoolean(8, false);
        ps.execute();
    }
}
