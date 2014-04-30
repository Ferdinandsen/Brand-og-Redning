package BLL;

import BE.BEAppearance;
import DAL.DALAppearance;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shadowleet
 */
public class BLLAppearance {

    DAL.DALAppearance dalAppearance;
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
    public void populateAppearances(){
        try {
            dalAppearance.populateAppearances();
        } catch (SQLException ex) {
            System.out.println("fejl i bllAppearance " + ex);
        }
    }
    public ArrayList<BEAppearance> getAppearances(){
        return dalAppearance.getAppearances();
    }
}
