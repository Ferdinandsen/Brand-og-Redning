package BLL;

import BE.BEVehicle;
import DAL.DALTimelist;
import BE.BEFireman;
import BE.BETime;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author Jacob
 */
public class BLLTimelist {

    DAL.DALTimelist daltime;
    private static BLLTimelist m_instance = null;

    private BLLTimelist() {
        try {
            daltime = DALTimelist.getInstance();
        } catch (SQLException e) {
            System.out.println("Fejl i BLLTimeList" + e);
        }
    }

    public static BLLTimelist getInstance() {
        if (m_instance == null) {
            m_instance = new BLLTimelist();
        }
        return m_instance;
    }

    public void sendToDAL(BETime time, BEVehicle odin, boolean hl, boolean ch, boolean st) {
//        daltime.updateTime(time, odin, hl, ch, st);
    }

    
    private Timestamp time() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        Timestamp currentTime = new java.sql.Timestamp(now.getTime());
        return currentTime;
    }

    public void createCheckInTimestamp(BEFireman fm) {
        System.out.println("check in");
        try {
            BETime temptime = new BETime(fm, time(), null, false);
            daltime.createCheckInTimestamp(temptime);
            System.out.println(temptime.getCheckIn());
        } catch (SQLException ex) {
            System.out.println("kunne ikke lave timestamp" + ex);
        }
    }

    public void createCheckOutTimestamp(BEFireman fireman) {
        System.out.println("check ud");

        try {
            BETime localTime = null;
            for (BETime theTime : daltime.getAllTimes()) {
                if (theTime.getFireman().getMedarbjeder().getMedarbejderNo() == fireman.getMedarbjeder().getMedarbejderNo() && theTime.getCheckOut() == null) {
                    theTime.setCheckOut(time());
                    localTime = theTime;
                }
            }
            daltime.createCheckOutTimestamp(localTime);
            localTime.setHasCheckedOut(true);
            System.out.println(localTime.getCheckOut());

        } catch (SQLException ex) {
            System.out.println("kunne ikke lave timestamp" + ex);
        }
    }

}
