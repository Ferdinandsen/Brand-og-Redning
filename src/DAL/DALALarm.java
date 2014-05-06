package DAL;

import BE.BEAlarm;
import BE.BEAlarmKøtj;
import BE.BEVehicle;
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
public class DALALarm {

    private Connection m_connection;
    private static DALALarm m_instance = null;
    ArrayList<BEAlarm> allAlarms;
    ArrayList<BEAlarmKøtj> allVeh;
    ArrayList<BEAlarmKøtj> allVehWithApproved;
    DALVehicle dalVehicle;

    private DALALarm() throws SQLServerException, SQLException {
        m_connection = DBConnection.getInstance().getConnection();
        dalVehicle = DALVehicle.getInstance();
        populateAlarm();
        populateAlarmKøretøj();
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
            int eva = result.getInt("evaNo");
            Timestamp tid = result.getTimestamp("tid");
            String desc = result.getString("beskrivelse");
       
            BEAlarm alarm = new BEAlarm(eva, tid, desc);
            allAlarms.add(alarm);
        }
    }

    public ArrayList<BEAlarm> getAllAlarms() {
        return allAlarms;
    }

    private void populateAlarmKøretøj() throws SQLException {
        allVeh = new ArrayList<>();
        String sql = "SELECT * FROM AlarmKøtj";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();
        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            int evaNoRef = result.getInt("evaNoRef");
            int køtjRef = result.getInt("køtjRef");
            boolean confirm = result.getBoolean("teamConfirmed");

            BEVehicle localVehicle = null;
            for (BEVehicle veh : dalVehicle.getVehicles()) {
                if (veh.getOdinnummer() == køtjRef) {
                    localVehicle = veh;
                }
            }
            BEAlarm localAlarm = null;
            for (BEAlarm alarm : allAlarms) {
                if (alarm.getEvaNo() == evaNoRef) {
                    localAlarm = alarm;
                }
            }

            BEAlarmKøtj veh = new BEAlarmKøtj(id, localAlarm, localVehicle, confirm);
            allVeh.add(veh);
        }
    }
    public ArrayList<BEAlarmKøtj> getAllAlarmKøtj(){
        return allVeh;
    }
}
