package DAL;

import BE.BEEmployee;
import BE.BEFireman;
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
public class DALFireman {
    Connection m_connection;
    private static DALFireman m_instance = null;
    private ArrayList<BEFireman> firemen;
    DALEmployee dalEmployee = DALEmployee.getInstance();

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

    public void populateFiremen() throws SQLException {
        firemen = new ArrayList<>();
        String sql = "SELECT * FROM Deltidsbrandmand";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();

        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int medarbejderRef = result.getInt("medarbejderRef");
            boolean holdleder = result.getBoolean("holdleder");
            boolean chauffør = result.getBoolean("chauffør");
            int team = result.getInt("team");
            BEEmployee localEmployee = null;

            for (BEEmployee employee : dalEmployee.getAllEmployees()) {
                if (employee.getMedarbejderNo() == medarbejderRef) {
                    localEmployee = employee;
                }
            }
            BEFireman fireman = new BEFireman(localEmployee, holdleder, chauffør,team);
            firemen.add(fireman);
        }
    }

    public ArrayList<BEFireman> getAllFiremen() {
        return firemen;
    }

//    public void changeBit(BEFireman fireman) throws SQLException {
//        String sql = "UPDATE Deltidsbrandmand SET isCheckIn = ? WHERE medarbejderRef = ?";
//
//        PreparedStatement ps = m_connection.prepareStatement(sql);
//        ps.setBoolean(1, fireman.isCheckedin());
//        ps.setInt(2, fireman.getMedarbjeder().getMedarbejderNo());
//        ps.execute();
//    }
}
