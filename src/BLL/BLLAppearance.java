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

    public ArrayList<BEAppearance> getAppearancesWithSameAlarm(BEAlarm selectedAlarm) {
        ArrayList<BEAppearance> appearances = new ArrayList<>();
        for (BEAppearance appearance : getAllAppearancesNotHlGodkendt()) {
            if (appearance.getAlarm() == selectedAlarm) {
                appearances.add(appearance);
            }
        }
        return appearances;
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

    public void confirmTeam(BELogin log, BEAlarm alarm, String comment) throws Exception {
//        Timestamp time = time();
        for (BEAppearance appearance : newAppearances) {
            try {
                alarm.setHlBemærkning(comment);
                dalAlarm.setHlBemærkning(alarm);
                appearance.setHlGodkendt(true);
                appearance.setAlarm(alarm);
                appearance.setLogin(log);
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

    public void addAppearance(BEFireman fireman, BEAlarm alarm, BEVehicle veh, String checkOutTime, boolean hl, boolean ch, boolean st, int kørselstype) {
        try {
            String[] theCheckOutTime = checkOutTime.toString().split(":");
            Timestamp ts = new Timestamp(alarm.getTime().getYear(), alarm.getTime().getMonth(), alarm.getTime().getDate(), Integer.parseInt(theCheckOutTime[0]), Integer.parseInt(theCheckOutTime[1]), alarm.getTime().getSeconds(), alarm.getTime().getNanos());
            dalAppearance.createAppearance(fireman, calculateTotalTime(alarm, ts), ts, alarm, veh, checkOutTime, hl, ch, st, (int) kørselstype);
        } catch (SQLException ex) {
            System.out.println("fejl i add Appearance " + ex);
        }

    }

    public void updateKørselType(BEAppearance a, int kørselType) {
        a.setKørselsType(kørselType);
        try {
            dalAppearance.updateKørselType(a);
        } catch (SQLException ex) {
            System.out.println("Fejl i update kørseltype " + ex);

        }
    }

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
}
