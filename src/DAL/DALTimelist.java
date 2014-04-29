package DAL;

import BE.BEFireman;
import BE.BEVehicle;
import BE.BETime;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Jacob
 */
public class DALTimelist {

    private Connection m_connection;
    private static DALTimelist m_instance = null;
    DALFireman dalFireman = DALFireman.getInstance();
    private ArrayList<BETime> allTimes = new ArrayList<>();

    private DALTimelist() throws SQLServerException, SQLException {
        m_connection = DBConnection.getInstance().getConnection();
        populateTimes();
    }

    public static DALTimelist getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALTimelist();
        }
        return m_instance;
    }

    private void populateTimes() throws SQLException {
        String sql = "SELECT * FROM Tidsregistrering";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();

        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int deltidsbrandmandRef = result.getInt("deltidsbrandmandRef");
            Timestamp checkIn = result.getTimestamp("checkIn");
            Timestamp checkOut = result.getTimestamp("checkOut");
            boolean hasCheckedOut = result.getBoolean("hasCheckedOut");
            BEFireman localFireman = null;

            for (BEFireman fireman : dalFireman.getAllFiremen()) {
                if (fireman.getMedarbjeder().getMedarbejderNo() == deltidsbrandmandRef) {
                    localFireman = fireman;
                }
            }
            BETime time = new BETime(localFireman, checkIn, checkOut, hasCheckedOut);
            allTimes.add(time);
        }
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

    public void updateTime(BETime time, BEVehicle odin, boolean hl, boolean ch, boolean st) {

    }
    public ArrayList<BETime> getAllTimes() {
        return allTimes;
    }
}
