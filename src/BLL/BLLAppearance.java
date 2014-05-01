package BLL;

import BE.BEAppearance;
import BE.BEVehicle;
import DAL.DALAppearance;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Shadowleet
 */
public class BLLAppearance {

    DALAppearance dalAppearance;
    private static BLLAppearance m_instance = null;
    public ArrayList<BEAppearance> getAppearances;

    private BLLAppearance() {
        try {
            dalAppearance = DALAppearance.getInstance();
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

    public ArrayList<BEAppearance> getAppearances() {
        return dalAppearance.getAppearances();
    }

    public ArrayList<BEAppearance> getAppearancesWithCriteria(Date date, String text, BEVehicle selectedItem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
