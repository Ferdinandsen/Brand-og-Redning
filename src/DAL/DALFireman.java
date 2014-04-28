package DAL;

import BE.BEEmployee;
import BE.BEFireman;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Jakob
 */
public class DALFireman {

    Connection m_connection;
    private static DALFireman m_instance = null;
    public ArrayList<BEFireman> firemen = new ArrayList<>();
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

            for (BEEmployee employee : dalEmployee.getAllEmployees()) {
                if (employee.getMedarbejderNo() == medarbejderRef) {
                    localEmployee = employee;
                }
            }

            BEFireman fireman = new BEFireman(localEmployee, holdleder, chauffør, isCheckIn);
            firemen.add(fireman);
        }
    }

    public ArrayList<BEFireman> getAllfiremen() {
        return firemen;
    }

    public void changeBit(BEFireman fireman) throws SQLException {
        String sql = "Update Deltidsbrandmand set isCheckIn = ? where medarbejderRef = ?";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setBoolean(1, fireman.isCheckedin());
        ps.setInt(2, fireman.getMedarbjeder().getMedarbejderNo());
        ps.execute();
    }

    public void createTimestamp(BEFireman fb) throws SQLException {
        String sql = "INSERT INTO Tidsregistrering VALUES (?,?, ?);";

        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, fb.getMedarbjeder().getMedarbejderNo());
        ps.setTimestamp(2, currentTimestamp);
        ps.setTimestamp(3, null);
        ps.execute();
    }
}
