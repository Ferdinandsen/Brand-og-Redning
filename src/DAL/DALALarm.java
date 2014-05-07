package DAL;

import BE.BEAlarm;
import BE.BEVehicle;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Team Kawabunga
 */
public class DALALarm {

    private Connection m_connection;
    private static DALALarm m_instance = null;
    ArrayList<BEAlarm> allAlarms;
    DALVehicle dalVehicle;

    private DALALarm() throws SQLServerException, SQLException {
        m_connection = DBConnection.getInstance().getConnection();
        dalVehicle = DALVehicle.getInstance();
        populateAlarm();
    }

    public static DALALarm getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALALarm();
        }
        return m_instance;
    }

    private void populateAlarm() throws SQLException {
        allAlarms = new ArrayList<>();
        String sql = "SELECT * FROM Alarm";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();
        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            int eva = result.getInt("evaNo");
            Timestamp tid = result.getTimestamp("tid");
            String desc = result.getString("beskrivelse");
            boolean done = result.getBoolean("done");

            BEAlarm alarm = new BEAlarm(id, eva, tid, desc, done);
            allAlarms.add(alarm);
        }
    }

    public ArrayList<BEAlarm> getAllAlarms() {
        return allAlarms;
    }

    public BEAlarm createAlarm(Date date, String fremmøde, String time) throws SQLException {
        String sql = "INSERT INTO Alarm VALUES (?,?,?,?)select @@identity";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        String[] parts = time.split(":");
        int selectedHour = Integer.parseInt(parts[0]);
        int selectedMin = Integer.parseInt(parts[1]);
        Timestamp ts = new Timestamp(date.getYear(), date.getMonth(), date.getDate(), selectedHour, selectedMin, 0, 0);

        ps.setString(1, null);
        ps.setTimestamp(2, ts);
        ps.setBoolean(3, false);
        ps.setString(4, fremmøde);
        ps.execute();
        
        ResultSet rs = ps.getGeneratedKeys();
        int id = 0;
        if (rs.next()){
            id = rs.getInt(1);
        }
        BEAlarm alarm = new BEAlarm(id, 0, ts, fremmøde, false);
        allAlarms.add(alarm);
        return alarm;

    }
}

