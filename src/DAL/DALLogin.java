package DAL;

import BE.BEEmployee;
import BE.BELogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Team Kawabunga
 */
public class DALLogin {

    Connection m_connection;
    private static DALLogin m_instance = null;
    private DALEmployee dalEmployee;
    private ArrayList<BELogin> allLogins = new ArrayList<>();

    private DALLogin() throws SQLException {
        m_connection = DBConnection.getInstance().getConnection();
        dalEmployee = DALEmployee.getInstance();
        populateLogins();
    }

    public static DALLogin getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALLogin();
        }
        return m_instance;
    }

    /**
     * Populate the ArrayList allLogins
     * @throws SQLException 
     */
    private void populateLogins() throws SQLException {
        String sql = "SELECT * FROM Login";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();
        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int medarbejderRef = result.getInt("medarbejderRef");
            String kode = result.getString("kode");
            boolean admin = result.getBoolean("admin");
            boolean kontor = result.getBoolean("kontor");
            boolean holdleder = result.getBoolean("holdleder");

            BEEmployee localEmployee = null;
            for (BEEmployee emp : dalEmployee.getAllEmployees()) {
                if (medarbejderRef == emp.getMedarbejderNo()) {
                    localEmployee = emp;
                }
            }
            BELogin login = new BELogin(localEmployee, kode, admin, kontor, holdleder);
            allLogins.add(login);
        }
    }

    /**
     * returns the ArrayList
     * @return allLogins
     */
    public ArrayList<BELogin> getAllLogins() {
        return allLogins;
    }
}
