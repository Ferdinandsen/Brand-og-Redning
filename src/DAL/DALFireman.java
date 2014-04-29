package DAL;

import BE.BEEmployee;
import BE.BEFireman;
import BE.BETime;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
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
    private ArrayList<BEFireman> firemen = new ArrayList<>();
    private ArrayList<BETime> allTimes = new ArrayList<>();
    DALEmployee dalEmployee = DALEmployee.getInstance();

    private DALFireman() throws SQLServerException, SQLException {
        m_connection = DBConnection.getInstance().getConnection();
        populateFiremen();
        populateTimes();
    }

    public static DALFireman getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALFireman();
        }
        return m_instance;
    }

    private void populateTimes() throws SQLException {
        String sql = "select * from Tidsregistrering";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();

        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int deltidsbrandmandRef = result.getInt("deltidsbrandmandRef");
            Timestamp checkIn = result.getTimestamp("checkIn");
            Timestamp checkOut = result.getTimestamp("checkOut");
            boolean hasCheckedOut = result.getBoolean("hasCheckedOut");
            BEFireman localFireman = null;

            for (BEFireman fireman : getAllFiremen()) {
                if (fireman.getMedarbjeder().getMedarbejderNo() == deltidsbrandmandRef) {
                    localFireman = fireman;
                }
            }

            BETime time = new BETime(localFireman, checkIn, checkOut, hasCheckedOut);
            allTimes.add(time);
        }
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

    public ArrayList<BEFireman> getAllFiremen() {
        return firemen;
    }

    public ArrayList<BETime> getAllTimes() {
        return allTimes;
    }

    public void changeBit(BEFireman fireman) throws SQLException {
        String sql = "Update Deltidsbrandmand set isCheckIn = ? where medarbejderRef = ?";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setBoolean(1, fireman.isCheckedin());
        ps.setInt(2, fireman.getMedarbjeder().getMedarbejderNo());
        ps.execute();
    }

    public void createCheckInTimestamp(BETime time) throws SQLException {
        String sql = "INSERT INTO Tidsregistrering VALUES (?,?,?,?)";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, time.getFireman().getMedarbjeder().getMedarbejderNo());
        ps.setTimestamp(2, time.getCheckIn());
        ps.setTimestamp(3, null);
        ps.setBoolean(4, false);
        ps.execute();
        allTimes.add(time);
    }

    public void createCheckOutTimestamp(BETime time) throws SQLException {
        String sql = "UPDATE Tidsregistrering SET checkOut = ?, hasCheckedOut = ? WHERE deltidsbrandmandRef = ? and hasCheckedOut = ?";
        
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setTimestamp(1, time.getCheckOut());
        ps.setBoolean(2, true);
        ps.setInt(3, time.getFireman().getMedarbjeder().getMedarbejderNo());
        ps.setBoolean(4, false);
        ps.execute();
    }
}
