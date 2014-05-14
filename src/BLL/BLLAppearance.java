package BLL;

import BE.BEAlarm;
import BE.BEAppearance;
import BE.BEFireman;
import BE.BELogin;
import BE.BEVehicle;
import DAL.DALALarm;
import DAL.DALAction;
import DAL.DALAppearance;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Team Kawabunga
 */
public class BLLAppearance {

    public ArrayList<BEAppearance> newAppearances;
    DALAppearance dalAppearance;
    DALALarm dalAlarm;
    DALAction dalAction;
    private BLLAlarm bllAlarm;
    private BLLAction bllAction;
    private static BLLAppearance m_instance = null;

    private BLLAppearance() {
        try {
            bllAction = BLLAction.getInstance();
            bllAlarm = BLLAlarm.getInstance();
            dalAction = DALAction.getInstance();
            dalAlarm = DALALarm.getInstance();
            dalAppearance = DALAppearance.getInstance();
            newAppearances = getAllAppearances();
        } catch (SQLException e) {
            System.out.println("fejl i bllEmployee " + e);
        }
    }

    public static BLLAppearance getInstance() {
        if (m_instance == null) {
            m_instance = new BLLAppearance();
        }
        return m_instance;
    }

    public void populateAppearances() {
        try {
            dalAppearance.populateAppearances();
        } catch (SQLException ex) {
            System.out.println("fejl i bllAppearance1 " + ex);
        }
    }

    public ArrayList<BEAppearance> getAllAppearances() {
        return dalAppearance.getAppearances();
    }

    public ArrayList<BEAppearance> getAppearancesWithCriteria(BEAlarm selectedAlarm) {
        newAppearances = new ArrayList();
        for (BEAppearance appearance : getAllAppearances()) {
            if (appearance.getAlarm() == selectedAlarm) {
                newAppearances.add(appearance);
            }
//
////            String times[] = appearance.getTime().getCheckIn().toString().split(" ");
////            String times2[] = times[1].split(":");
////            String hourAndMin = times2[0] + ":" + times2[1];
////            int checkInHour = Integer.parseInt(times2[0]);
////            int checkInMin = Integer.parseInt(times2[1]);
//            String selectedTime[] = time.split(":");
//            int selectedHour = Integer.parseInt(selectedTime[0]);
//            
//            int selectedMin = Integer.parseInt(selectedTime[1]);
//            Timestamp ts = new Timestamp(date.getYear(), date.getMonth(), date.getDate(), selectedHour, selectedMin, 0, 0);
//            long testMili = ts.getTime();
//            long checkMili = appearance.getAlarm().getTime().getTime();//appearance.getTime().getCheckIn().getTime();
//            long minutes = (checkMili - testMili) / 1000 / 60;
//            System.out.println(minutes);
//            if (minutes >= 0) { //dagene + tid (10min) passer!
//                newAppearances.add(appearance);
//            }
        }
        return newAppearances;
    }

    private Timestamp time() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        Timestamp currentTime = new java.sql.Timestamp(now.getTime());
        return currentTime;
    }

    public void createEndShift(BEAlarm alarm, BEFireman fireman, BEVehicle veh, boolean hl, boolean ch, boolean st) {
        Timestamp time = time();
        int total = calculateTotalTime(alarm, time);

        try {
            dalAppearance.endShift(alarm, fireman, veh, hl, ch, st, total, time);
        } catch (SQLException e) {
            System.out.println("kunne ikke oprette end shift " + e);
        }
    }

    private int calculateTotalTime(BEAlarm alarm, Timestamp ts) {
        Timestamp ci = alarm.getTime();
        Timestamp co = ts;

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

    public void confirmTeam(BELogin log, int kørselstype, BEAlarm alarm, String comment) throws Exception {
        Timestamp time = time();
        for (BEAppearance appearance : newAppearances) {
            try {
                appearance.setType(kørselstype);
                appearance.setAlarm(alarm);
                dalAppearance.confirmTeam(appearance);
                createAction(log, time, appearance, kørselstype, alarm, comment);
            } catch (SQLException ex) {
                System.out.println("fejl i confirmTeam" + ex);
            }
        }
    }

    private void createAction(BELogin log, Timestamp time, BEAppearance appearance, int kørselstype, BEAlarm alarm, String comment) {
        try {
            dalAction.createAction(log, time, appearance, kørselstype, alarm, comment);
        } catch (SQLException ex) {
            System.out.println("fej i createAction " + ex);
        }
    }

    public void update() {
        try {
            dalAppearance.populateAppearances();
        } catch (SQLException ex) {
            System.out.println("blabla" + ex);
        }
    }

    public void updateAppearanceTotal(BEAppearance appearance, int total) {
        try {
            dalAppearance.updateTime(appearance, total);
        } catch (SQLException ex) {
            System.out.println("fejl i updateAppearanceTotal " + ex);
        }
    }
}
