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
public class BLLAlarm{

    private static BLLAlarm m_instance = null;
    DALALarm dalAlarm;

    private BLLAlarm() {
        try {
            dalAlarm = DALALarm.getInstance();

        } catch (SQLException ex) {
            System.out.println("fejl i dalAlarm: " + ex.getMessage());
        }
    }

    public void update() {
        try {
            dalAlarm.populateAlarm();
        } catch (SQLException ex) {
             System.out.println("fejl i dalAlarm: " + ex.getMessage());
        }
    }

    /**
     *
     * @return a new instance, if an instance isn't already created
     */
        public static BLLAlarm getInstance() {
        if (m_instance == null) {
            m_instance = new BLLAlarm();
        }
        return m_instance;
    }

    /**
     *
     * @return all alarms
     */
    public ArrayList<BEAlarm> getAllAlarms() {
        return dalAlarm.getAllAlarms();
    }

    /**
     *
     * @return all OdinAlarms (only used for our main screen)
     */
    public ArrayList<BEOdinAlarm> getAllOdinAlarms() {
        return dalAlarm.getAllOdinAlarms();
    }

    /**
     *
     * @return all unfinished alarms (boolean done set to false)
     */
    public ArrayList<BEAlarm> getAllUnfinishedAlarms() {
        ArrayList<BEAlarm> unfinishedAlarms = new ArrayList<>();
        for (BEAlarm alarm : getAllAlarms()) {
            if (!alarm.isDone()) {
                unfinishedAlarms.add(alarm);
            }
        }
        return unfinishedAlarms;
    }

    /**
     *
     * @return all alarms, that a HL haven't confirmed
     */
    public ArrayList<BEAlarm> getAllIkkeHLGodkendt() {
        ArrayList<BEAlarm> hlgodkendt = new ArrayList<>();
        for (BEAlarm a : getAllAlarms()) {
            if (a.getHlGodkendtTid() == null) {
                hlgodkendt.add(a);
            }
        }
        return hlgodkendt;
    }

    /**
     *
     * @return all alarms, that are HL confirmed but NOT IL confirmed
     */
    public ArrayList<BEAlarm> getAllHLGodkendtAndNotILGodkend() {
        ArrayList<BEAlarm> hlgodkendt = new ArrayList<>();
        for (BEAlarm a : getAllAlarms()) {
            if (a.getHlGodkendtTid() != null && a.getIlGodkendtTid() == null) {
                hlgodkendt.add(a);
            }
        }
        return hlgodkendt;
    }

    /**
     *
     * @param a
     */
    public void updateAlarm(BEAlarm a) {
        try {
            dalAlarm.updateAlarm(a);
        } catch (SQLException ex) {
            System.out.println("fejl i update alarm" + ex);
        }
    }

    /**
     *
     * @return the time right NOW
     */
    private Timestamp time() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        Timestamp currentTime = new java.sql.Timestamp(now.getTime());
        return currentTime;
    }

    /**
     *
     * @param localAlarm
     */
    public void confirmAlarm(BEAlarm localAlarm) {
        Timestamp time = time();
        try {
            dalAlarm.confirmAlarm(localAlarm, time);
            localAlarm.setIlGodkendtTid(time);
        } catch (SQLException ex) {
            System.out.println("fejl i confirm alarm" + ex);
        }
    }


      }
