package DAL;

import BE.BEEmployee;
import BE.BEFireman;
import DAL.DALEmployee;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jakob
 */
public class DALFireman {

    Connection m_connection;

    private static DALFireman m_instance = null;
    public ArrayList<BEFireman> firemen = new ArrayList<>();

    private DALFireman() throws SQLServerException, SQLException {
        m_connection = DBConnection.getInstance().getConnection();
        populateFiremen();
    }

    public static DALFireman getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALFireman();
        }
        return m_instance;
    }

    private void populateFiremen() throws SQLException {
        String sql = "select * from Deltidsbrandmand";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();

        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int medarbejderRef = result.getInt("medarbejderRef");
            boolean holdleder = result.getBoolean("holdleder");
            boolean chauffør = result.getBoolean("chauffør");
            boolean isCheckIn = result.getBoolean("isCheckIn");
            BEEmployee localEmployee = null;
           
            for (BEEmployee employee : DALEmployee.getInstance().getAllEmployess() ) {
                if (employee.getMedarbejderNo() == medarbejderRef) {
                    localEmployee = employee;
                }
            }
            
            BEFireman fireman = new BEFireman(localEmployee, holdleder, chauffør, isCheckIn);
            firemen.add(fireman);
        }
    }
    
    public ArrayList<BEFireman> getAllfiremen(){
        return firemen;
    }
}
