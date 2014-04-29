package DAL;

import BE.BETime;
import BE.BEVehicle;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Shadowleet
 */
public class DALAppearance {

    private Connection m_connection;
    private static DALAppearance m_instance = null;

    private DALAppearance() throws SQLServerException, SQLException {
        m_connection = DBConnection.getInstance().getConnection();

    }

    public static DALAppearance getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALAppearance();
        }
        return m_instance;
    }

    public void endShift(BETime tm, BEVehicle veh, boolean hl, boolean ch, boolean st, int total) throws SQLException {
        String sql = "INSERT INTO Fremmøde VALUES (?,?,?,?,?,?,?,?)";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        if (veh == null)
            ps.setString(1, null);
        else
        {  
        ps.setInt(1, veh.getOdinnummer());
        }
        ps.setInt(2, tm.getId());
        ps.setInt(3, total);
        ps.setInt(4, -1);
        ps.setBoolean(5, false);
        ps.setBoolean(6, hl);
        ps.setBoolean(7, ch);
        ps.setBoolean(8, st);
        ps.execute();
                
        
    }
}
