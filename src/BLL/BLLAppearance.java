package BLL;

import BE.BEAlarm;
import BE.BEAlarmKøtj;
import BE.BEAppearance;
import BE.BEVehicle;
import DAL.DALAppearance;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    private BLLAlarm bllAlarm;
    private static BLLAppearance m_instance = null;

    private BLLAppearance() {
        try {
            bllAlarm = BLLAlarm.getInstance();
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

    public ArrayList<BEAppearance> getAppearancesWithCriteria(Date date, String time, int tolerance) {
        newAppearances = new ArrayList();
        for (BEAppearance appearance : getAllAppearances()) {

//            String times[] = appearance.getTime().getCheckIn().toString().split(" ");
//            String times2[] = times[1].split(":");
//            String hourAndMin = times2[0] + ":" + times2[1];
//            int checkInHour = Integer.parseInt(times2[0]);
//            int checkInMin = Integer.parseInt(times2[1]);
            String selectedTime[] = time.split(":");
            int selectedHour = Integer.parseInt(selectedTime[0]);
            int selectedMin = Integer.parseInt(selectedTime[1]);
            Timestamp test = new Timestamp(date.getYear(), date.getMonth(), date.getDate(), selectedHour, selectedMin, 0, 0);
            long testMili = test.getTime();
            long checkMili = appearance.getTime().getCheckIn().getTime();
            long minutes = (checkMili - testMili) / 1000 / 60;
            if (minutes >= 0 && minutes <= tolerance) { //dagene + tid (10min) passer!
                newAppearances.add(appearance);
            }
        }
        return newAppearances;
    }

    public void confirmTeam(int type, String fremmøde) throws Exception {
        for (BEAppearance appearance : newAppearances) {
            try {
                appearance.setType(type);
                appearance.getAlarmVehicle().setIsConfirmed(true);
                dalAppearance.confirmTeam(appearance);
            } catch (SQLException ex) {
                System.out.println("fejl i bllAppearance2 " + ex);
            
            }
        }
    }
    private BEAlarmKøtj getAlarmKøretøjByAlarm(BEAlarm alarm){
        for (BEAlarmKøtj alkt : bllAlarm.getAllAlarmKøtj()){
            if (alkt.getAlarm() == alarm)
                return alkt;
        }
        return null;
    }
}
