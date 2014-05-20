package BLL;

import BE.BEAlarm;
import BE.BEOdinAlarm;
import DAL.DALALarm;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

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

    public ArrayList<BEAlarm> getAllIkkeHLGodkendt() {
        ArrayList<BEAlarm> hlgodkendt = new ArrayList<>();
        for (BEAlarm a : getAllAlarms()) {
            if (a.getHlGodkendtTid() == null) {
                hlgodkendt.add(a);
            }
        }
        return hlgodkendt;
    }

    public void updateAlarm(BEAlarm a) {
        try {
            dalAlarm.updateAlarm(a);
        } catch (SQLException ex) {
            System.out.println("fejl i update alarm" + ex);
        }
    }

    private Timestamp time() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        Timestamp currentTime = new java.sql.Timestamp(now.getTime());
        return currentTime;
    }

    public void confirmAlarm(BEAlarm localAlarm) {
        try {
            dalAlarm.confirmAlarm(localAlarm, time());
        } catch (SQLException ex) {
            System.out.println("fejl i confirm alarm" + ex);
        }
    }

    public void setGuestsOnAlarms() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
