package BLL;

import BE.BEAlarm;
import BE.BEAppearance;
import BE.BEFireman;
import BE.BELogin;
import BE.BEVehicle;
import DAL.DALALarm;
import DAL.DALAppearance;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Team Kawabunga
 */
public class BLLAppearance {

    DALAppearance dalAppearance;
    DALALarm dalAlarm;
    private static BLLAppearance m_instance = null;

    private BLLAppearance() {
        try {
            dalAlarm = DALALarm.getInstance();
            dalAppearance = DALAppearance.getInstance();
        } catch (SQLException e) {
            System.out.println("fejl i bllAppearance " + e);
        }
    }

    /**
     *
     * @return
     */
    public static BLLAppearance getInstance() {
        if (m_instance == null) {
            m_instance = new BLLAppearance();
        }
        return m_instance;
    }

    /**
     *
     */
    public void populateAppearances() {
        try {
            dalAppearance.populateAppearances();
        } catch (SQLException ex) {
            System.out.println("fejl i bllAppearance1 " + ex);
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<BEAppearance> getAllAppearances() {
        return dalAppearance.getAppearances();
    }

    /**
     *
     * @return
     */
    public ArrayList<BEAppearance> getAllAppearancesNotHlGodkendt() {
        ArrayList<BEAppearance> notHlGodkendt = new ArrayList<>();
        for (BEAppearance appearance : getAllAppearances()) {
            if (!appearance.isHlGodkendt()) {
                notHlGodkendt.add(appearance);
            }
        }
        return notHlGodkendt;
    }

    /**
     *
     * @param alarm
     * @return
     */
    public ArrayList<BEAppearance> getAllHlGodkendtAppearances(BEAlarm alarm) {
        ArrayList<BEAppearance> hlGodkendtAppearances = new ArrayList<>();
        for (BEAppearance appearance : getAllAppearances()) {
            if (appearance.getAlarm() == alarm && appearance.isHlGodkendt()) {
                hlGodkendtAppearances.add(appearance);
            }
        }
        return hlGodkendtAppearances;
    }

    /**
     *
     * @param selectedAlarm
     * @return
     */
    public ArrayList<BEAppearance> getAppearancesWithSameAlarm(BEAlarm selectedAlarm) {
        ArrayList<BEAppearance> appearances = new ArrayList<>();
        for (BEAppearance appearance : getAllAppearancesNotHlGodkendt()) {
            if (appearance.getAlarm() == selectedAlarm) {
                appearances.add(appearance);
            }
        }
        return appearances;
    }

    /**
     *
     * @param fm
     * @param from
     * @param to
     * @return
     */
    public ArrayList<BEAppearance> getAllAppearancesWithDateCriteria(BEFireman fm, Date from, Date to) {
        ArrayList<BEAppearance> dateappearance = new ArrayList<>();
        for (BEAppearance a : getAllAppearances()) {
            if (fm == null && a.getAlarm().getTime().after(from) && a.getAlarm().getTime().before(to) && a.isIlGodkendt() && !a.isLønDone()) {
                dateappearance.add(a);
            } else if (a.getAlarm().getTime().after(from) && a.getAlarm().getTime().before(to) && a.getFireman().equals(fm) && a.isIlGodkendt() && !a.isLønDone()) {
                dateappearance.add(a);
            }
        }
        return dateappearance;
    }

    /**
     *
     * @return
     */
    private Timestamp time() {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        Timestamp currentTime = new java.sql.Timestamp(now.getTime());
        return currentTime;
    }

    /**
     *
     * @param alarm
     * @param fireman
     * @param veh
     * @param hl
     * @param ch
     * @param st
     */
    public void createEndShift(BEAlarm alarm, BEFireman fireman, BEVehicle veh, boolean hl, boolean ch, boolean st) {
        Timestamp time = time();
        int total = calculateTotalTime(alarm.getTime(), time);

        try {
            dalAppearance.endShift(alarm, fireman, veh, hl, ch, st, total, time);
        } catch (SQLException e) {
            System.out.println("kunne ikke oprette end shift " + e);
        }
    }

    /**
     *
     * @param checkin
     * @param checkout
     * @return
     */
    public int calculateTotalTime(Timestamp checkin, Timestamp checkout) {
        Timestamp ci = checkin;
        Timestamp co = checkout;

        long total = (co.getTime() - ci.getTime());
        long second = total / 1000 % 60;
        long minute = total / (1000 * 60) % 60;
        long hour = total / (60 * 60 * 1000);
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

    /**
     * sender det forkerte med vidre!
     *
     * @param log
     * @param alarm
     * @param comment
     * @throws Exception
     */
    public void confirmTeam(BELogin log, BEAlarm alarm, String comment) throws Exception {
        for (BEAppearance appearance : getAppearancesWithSameAlarm(alarm)) {
            alarm.setHlBemærkning(comment);
            alarm.setHlGodkendtTid(time());
            appearance.setHlGodkendt(true); // HER ELLER I DAL?
            appearance.setAlarm(alarm);
            appearance.setLogin(log);
            try {
                dalAlarm.setHlBemærkning(alarm);
                dalAppearance.confirmTeam(appearance);
            } catch (SQLException ex) {
                System.out.println("fejl i confirmTeam" + ex);
            }
        }
    }

    /**
     *
     */
    public void update() {
        try {
            dalAppearance.populateAppearances();
        } catch (SQLException ex) {
            System.out.println("fejl i update inde i appearance" + ex);
        }
    }

    /**
     *
     * @param appearance
     */
    public void updateAppearance(BEAppearance appearance) {
        try {
            dalAppearance.updateAppearance(appearance);
        } catch (SQLException ex) {
            System.out.println("fejl i updateAppearanceTotal " + ex);
        }
    }

    /**
     *
     * @param appearance
     */
    public void deleteAppearance(BEAppearance appearance) {
        try {
            dalAppearance.deleteAppearance(appearance);
        } catch (SQLException ex) {
            System.out.println("fejl i delete Appearance " + ex);
        }
    }

    /**
     *
     * @param fireman
     * @param alarm
     * @param veh
     * @param ts
     * @param hl
     * @param ch
     * @param st
     * @param kørselstype
     */
    public void addAppearance(BEFireman fireman, BEAlarm alarm, BEVehicle veh, Timestamp ts, boolean hl, boolean ch, boolean st, int kørselstype) {
        try {
            dalAppearance.createAppearance(fireman, calculateTotalTime(alarm.getTime(), ts), ts, alarm, veh, String.valueOf(ts.getHours() + ts.getMinutes()), hl, ch, st, (int) kørselstype);
        } catch (SQLException ex) {
            System.out.println("fejl i add Appearance " + ex);
        }

    }

    /**
     *
     * @param a
     * @param kørselType
     */
    public void updateKørselType(BEAppearance a, int kørselType) {
        a.setKørselsType(kørselType);
        try {
            dalAppearance.updateKørselType(a);
        } catch (SQLException ex) {
            System.out.println("Fejl i update kørseltype " + ex);
        }
    }

    /**
     *
     * @param alarm
     */
    public void confirmAlarmTeam(BEAlarm alarm) {
        for (BEAppearance appearance : getAllHlGodkendtAppearances(alarm)) {
            try {
                dalAppearance.confirmAlarmTeam(appearance);
                appearance.setIlGodkendt(true);
            } catch (SQLException ex) {
                System.out.println("Fejl i confirm alarm " + ex);
            }
        }
    }

    /**
     *
     * @param appearance
     */
    public void updateFunction(BEAppearance appearance) {
        try {
            dalAppearance.updateFunction(appearance);
        } catch (SQLException ex) {
            System.out.println("fejl i updateFunction " + ex);
        }
    }

    /**
     *
     * @param app
     */
    public void EndSalary(ArrayList<BEAppearance> app) {
        Timestamp time = time();
        try {
            for (BEAppearance a : app) {
                dalAppearance.createSalary(a, time);
                a.setLønDone(true);
                a.setLønTime(time);
            }
        } catch (SQLException ex) {
            System.out.println("Fejl i endSalary" + ex);
        }
    }
}
