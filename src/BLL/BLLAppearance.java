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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Team Kawabunga
 */
public class BLLAppearance {

    public ArrayList<BEAppearance> newAppearances;
    DALAppearance dalAppearance;
    DALALarm dalAlarm;
    private BLLAlarm bllAlarm;
    private static BLLAppearance m_instance = null;

    private BLLAppearance() {
        try {
            bllAlarm = BLLAlarm.getInstance();

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

    public ArrayList<BEAppearance> getAllAppearancesNotHlGodkendt() {
        ArrayList<BEAppearance> notHlGodkendt = new ArrayList<>();
        for (BEAppearance appearance : getAllAppearances()) {
            if (!appearance.isHlGodkendt()) {
                notHlGodkendt.add(appearance);
            }
        }
        return notHlGodkendt;
    }

    public ArrayList<BEAppearance> getAllAppearances() {
        return dalAppearance.getAppearances();
    }

    public ArrayList<BEAppearance> getAllHlGodkendtAppearances(BEAlarm alarm) {
        ArrayList<BEAppearance> hlGodkendtAppearances = new ArrayList<>();
        for (BEAppearance appearance : getAllAppearances()) {
            if (appearance.getAlarm() == alarm && appearance.isHlGodkendt()) {
                hlGodkendtAppearances.add(appearance);
            }
        }
        return hlGodkendtAppearances;
    }

    public ArrayList<BEAppearance> getAppearancesWithCriteria(BEAlarm selectedAlarm) {
        newAppearances = new ArrayList();
        for (BEAppearance appearance : getAllAppearancesNotHlGodkendt()) {
            if (appearance.getAlarm() == selectedAlarm) {
                newAppearances.add(appearance);
            }
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

    public void confirmTeam(BELogin log, BEAlarm alarm, String comment) throws Exception {
//        Timestamp time = time();
        for (BEAppearance appearance : newAppearances) {
            try {
                appearance.setHlGodkendt(true);
                appearance.setAlarm(alarm);
                dalAppearance.confirmTeam(appearance, comment);

            } catch (SQLException ex) {
                System.out.println("fejl i confirmTeam" + ex);
            }
        }
    }

    public void update() {
        try {
            dalAppearance.populateAppearances();
        } catch (SQLException ex) {
            System.out.println("fejl i update inde i appearance" + ex);
        }
    }

    public void updateAppearance(BEAppearance appearance) {
        try {
            dalAppearance.updateAppearance(appearance);
        } catch (SQLException ex) {
            System.out.println("fejl i updateAppearanceTotal " + ex);
        }
    }

    public void deleteAppearance(BEAppearance appearance) {
        try {
            dalAppearance.deleteAppearance(appearance);
        } catch (SQLException ex) {
            System.out.println("fejl i delete Appearance " + ex);
        }
    }

    public void addAppearance(BEFireman fireman, BEAlarm alarm, BEVehicle veh, String checkOutTime, boolean hl, boolean ch, boolean st) {
        try {
            dalAppearance.createAppearance(fireman, calculateTotalTime(alarm, time()), time(), alarm, veh, checkOutTime, hl, ch, st);
        } catch (SQLException ex) {
            System.out.println("fejl i add Appearance " + ex);
        }
    }
}
