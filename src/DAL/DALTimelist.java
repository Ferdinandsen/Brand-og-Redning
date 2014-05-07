package DAL;

import BE.BEFireman;
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
 * @author Team Kawabunga
 */
public class DALTimelist {
    private Connection m_connection;
    private static DALTimelist m_instance = null;
    DALFireman dalFireman = DALFireman.getInstance();
    private ArrayList<BETime> allTimes;

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

    public void populateTimes() throws SQLException {
        allTimes = new ArrayList<>();
        String sql = "SELECT * FROM Tidsregistrering";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();

        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            int deltidsbrandmandRef = result.getInt("deltidsbrandmandRef");
            Timestamp checkIn = result.getTimestamp("checkIn");
            Timestamp checkOut = result.getTimestamp("checkOut");
            boolean hasCheckedOut = result.getBoolean("hasCheckedOut");
            boolean complete = result.getBoolean("complete");
            BEFireman localFireman = null;

            for (BEFireman fireman : dalFireman.getAllFiremen()) {
                if (fireman.getMedarbjeder().getMedarbejderNo() == deltidsbrandmandRef) {
                    localFireman = fireman;
                }
            }
            BETime time = new BETime(id, localFireman, checkIn, checkOut, hasCheckedOut, complete);
            allTimes.add(time);
        }
    }

    public void createCheckInTimestamp(BETime time) throws SQLException {
        String sql = "INSERT INTO Tidsregistrering VALUES (?,?,?,?,?)select @@identity";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, time.getFireman().getMedarbjeder().getMedarbejderNo());
        ps.setTimestamp(2, time.getCheckIn());
        ps.setTimestamp(3, null);
        ps.setBoolean(4, false);
        ps.setBoolean(5, false);
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            int id = rs.getInt(1);
            time.setId(id);
        }
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

    public ArrayList<BETime> getAllTimes() {
        return allTimes;
    }
}
