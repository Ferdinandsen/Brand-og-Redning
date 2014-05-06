package BLL;

import BE.BEAlarmKøtj;
import DAL.DALTimelist;
import BE.BEFireman;
import BE.BETime;
import BE.BEVehicle;
import DAL.DALAppearance;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

/**
 *
 * @author Team Kawabunga
 */
public class BLLTimelist {

    DAL.DALTimelist daltime;
    DAL.DALAppearance dalappearance;
    private static BLLTimelist m_instance = null;

    private BLLTimelist() {
        try {
            daltime = DALTimelist.getInstance();
            dalappearance = DALAppearance.getInstance();
        } catch (SQLException e) {
            System.out.println("Fejl i BLLTimeList " + e);
        }
    }

    public static BLLTimelist getInstance() {
        if (m_instance == null) {
            m_instance = new BLLTimelist();
        }
        return m_instance;
    }
    public void update(){
        try {
            daltime.populateTimes();
        } catch (SQLException ex) {
            System.out.println("blabla" +ex);
        }
    }

    private Timestamp time() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        Timestamp currentTime = new java.sql.Timestamp(now.getTime());
        return currentTime;
    }

    public void createCheckInTimestamp(BEFireman fm) {
        try {
            BETime temptime = new BETime(fm, time(), null, false, false);
            daltime.createCheckInTimestamp(temptime);
        } catch (SQLException ex) {
            System.out.println("kunne ikke lave timestamp " + ex);
        }
    }

    public void createEndShift(BEFireman fireman, BEVehicle veh, boolean hl, boolean ch, boolean st) {
        BETime localTime = null;
        for (BETime theTime : daltime.getAllTimes()) {
            if (theTime.getFireman().getMedarbjeder().getMedarbejderNo() == fireman.getMedarbjeder().getMedarbejderNo() && theTime.getCheckOut() == null) {
                theTime.setCheckOut(time());
                localTime = theTime;
            }
        }
        int total = calculateTotalTime(localTime);
        try {
            daltime.createCheckOutTimestamp(localTime);
            localTime.setHasCheckedOut(true);
        } catch (SQLException ex) {
            System.out.println("kunne ikke lave timestamp " + ex);
        }
        try {
            dalappearance.endShift(localTime, veh, hl, ch, st, total);
        } catch (SQLException e) {
            System.out.println("kunne ikke oprette end shift " + e);
        }
    }

    /**
     * TEST!
     *
     * @param localTime
     */
    private int calculateTotalTime(BETime localTime) {
        Timestamp ci = localTime.getCheckIn();
        Timestamp co = localTime.getCheckOut();

        long total = (co.getTime() - ci.getTime());

        long second = total / 1000 % 60;
        long minute = total / (1000 * 60) % 60;
        long hour = total / (60 * 60 * 1000) % 24;
        if (second > 0) {
            minute++;
        }
        if (minute > 0) {
            hour++;
        }
        if (hour < 2) {
            hour = 2;
        }
        int d = Integer.parseInt("" + hour);
        return d;
    }

    public ArrayList<String> getDates() {
        TreeSet<String> test = new TreeSet<>();
        ArrayList<String> dates = new ArrayList<>();
        for (BETime time : daltime.getAllTimes()) {
            if (time.isHasCheckedOut()) {
                Date date = time.getCheckIn();
                long milisecs = date.getTime();
                test.add(new SimpleDateFormat("dd/MM-yyyy").format(new Date(milisecs)));
            }
        }
        dates.addAll(test);
        return dates;
    }
    
    public ArrayList<String> getTimes() {
        TreeSet<String> test = new TreeSet<>();
        ArrayList<String> times = new ArrayList<>();
        for (BETime time : daltime.getAllTimes()) {
            if (time.isHasCheckedOut()) {
                Date date = time.getCheckIn();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                System.out.println( sdf.format(date.getTime()) );
//                long milisecs = date.getTime();
//                test.add(new SimpleDateFormat("dd/MM-yyyy").format(new Date(milisecs)));
            }
        }
        times.addAll(test);
        return times;
    }
}
