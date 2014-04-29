package BLL;

import BE.BEFireman;
import BE.BETime;
import DAL.DALFireman;
import GUI.CheckInView;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Jakob
 */
public class BLLFireman {

    DALFireman dalFireman;
    private static BLLFireman m_instance = null;

    private BLLFireman() {
        try {
            dalFireman = DALFireman.getInstance();
        } catch (SQLException e) {
            System.out.println("fejl i DALFireman " + e);
        }
    }

    public static BLLFireman getInstance() {
        if (m_instance == null) {
            m_instance = new BLLFireman();
        }
        return m_instance;
    }

    public ArrayList<BEFireman> getAllfiremen() {
        return dalFireman.getAllFiremen();
    }

    public void changeBit(BEFireman fireman) {
        try {
            dalFireman.changeBit(fireman);
        } catch (SQLException ex) {
            System.out.println("kunne ikke changeBit" + ex);
        }
    }

    public void createCheckInTimestamp(BEFireman fm) {
        System.out.println("check in");
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        Timestamp currentTime = new java.sql.Timestamp(now.getTime());
        try {
            BETime temptime = new BETime(fm, currentTime, null, false);
            dalFireman.createCheckInTimestamp(temptime);
            System.out.println(temptime.getCheckIn());
        } catch (SQLException ex) {
            System.out.println("kunne ikke lave timestamp" + ex);
        }
    }

    public void createCheckOutTimestamp(BEFireman fireman) {
        System.out.println("check ud");
        Calendar calendar = Calendar.getInstance();
         java.util.Date now = calendar.getTime();
        Timestamp currentTime = new java.sql.Timestamp(now.getTime());
        try {
            BETime localTime = null;
            for (BETime theTime : dalFireman.getAllTimes()) {
                if (theTime.getFireman().getMedarbjeder().getMedarbejderNo() == fireman.getMedarbjeder().getMedarbejderNo() && theTime.getCheckOut() == null) {
                    theTime.setCheckOut(currentTime);
                    localTime = theTime;
                }
            }
            dalFireman.createCheckOutTimestamp(localTime);
            localTime.setHasCheckedOut(true);
            System.out.println(localTime.getCheckOut());

        } catch (SQLException ex) {
            System.out.println("kunne ikke lave timestamp" + ex);
        }
    }
}
