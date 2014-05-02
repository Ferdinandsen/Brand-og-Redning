package BLL;

import BE.BEAppearance;
import BE.BEVehicle;
import DAL.DALAppearance;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Team Kawabunga
 */
public class BLLAppearance {

    public ArrayList<BEAppearance> newAppearances;
    DALAppearance dalAppearance;
    private static BLLAppearance m_instance = null;

    private BLLAppearance() {
        try {
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
            System.out.println("fejl i bllAppearance " + ex);
        }
    }

    public ArrayList<BEAppearance> getAllAppearances() {
        return dalAppearance.getAppearances();
    }

    public ArrayList<BEAppearance> getAppearancesWithCriteria(Date date, String time, BEVehicle selectedItem) {
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
            if (minutes <= 10 && selectedItem == appearance.getVehicle()) { //dagene + tid (10min) passer!

                newAppearances.add(appearance);
            }
        }
        return newAppearances;
    }

    public void confirmTeam(int type) {
        for (BEAppearance appearance : newAppearances) {
            try {
                appearance.setType(type);
                dalAppearance.confirmTeam(appearance);
            } catch (SQLException ex) {
                System.out.println("fejl i bllAppearance " + ex);
            }
        }
    }
}
