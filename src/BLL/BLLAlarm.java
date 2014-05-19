package BLL;

import BE.BEAlarm;
import BE.BEOdinAlarm;
import DAL.DALALarm;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Team Kawabunga
 */
public class BLLAlarm {

    private static BLLAlarm m_instance = null;
    DALALarm dalAlarm;

    private BLLAlarm() {
        try {
            dalAlarm = DALALarm.getInstance();

        } catch (SQLException ex) {
            System.out.println("fejl i dalAlarm: " + ex.getMessage());
        }
    }

    public static BLLAlarm getInstance() {
        if (m_instance == null) {
            m_instance = new BLLAlarm();
        }
        return m_instance;
    }

    public ArrayList<BEAlarm> getAllAlarms() {
        return dalAlarm.getAllAlarms();
    }

    public ArrayList<BEOdinAlarm> getAllOdinAlarms() {
        return dalAlarm.getAllOdinAlarms();
    }

    public ArrayList<BEAlarm> getAllUnfinishedAlarms() {
        ArrayList<BEAlarm> unfinishedAlarms = new ArrayList<>();
        for (BEAlarm alarm : getAllAlarms()) {
            if (!alarm.isDone()) {
                unfinishedAlarms.add(alarm);
            }
        }
        return unfinishedAlarms;
    }

    public void updateAlarm(BEAlarm a) {
        try {    
            dalAlarm.updateAlarm(a);
        } catch (SQLException ex) {
            System.out.println("fejl i update alarm" + ex);
        }
    }
}
