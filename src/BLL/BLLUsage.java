package BLL;

import BE.BEAlarm;
import BE.BEMateriel;
import BE.BEUsage;
import DAL.DALUsage;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Team Kawabunga
 */
public class BLLUsage implements IBLLType{

    DALUsage dalusage;
    private static BLLUsage m_instance = null;

    private BLLUsage() {
        try {
            dalusage = DALUsage.getInstance();
            dalusage.populateUsages();
        } catch (SQLException e) {
            System.out.println("Fejl i BLLUsage " + e);
        }
    }

    /**
     * 
     * @return 
     */
    public ArrayList<BEUsage> getAllUsages() {
        return dalusage.getAllUsages();
    }

    /**
     * 
     * @return 
     */
    public static BLLUsage getInstance() {
        if (m_instance == null) {
            m_instance = new BLLUsage();
        }
        return m_instance;
    }

    /**
     * 
     * @param u 
     */
    public void createReport(BEUsage u) {
        try {
            dalusage.createUsageReport(u);

        } catch (SQLException ex) {
            System.out.println("Fejl i BLLUsage createReport " + ex);
        }
    }

    /**
     * 
     * @return 
     */
    public ArrayList<BEMateriel> getAllMats() {
        return dalusage.getAllMats();
    }

    /**
     * 
     * @param bu 
     */
    public void updateUsageReport(BEUsage bu) {
        try {
            dalusage.updateUsageReport(bu);
        } catch (SQLException e) {
            System.out.println("Fejl i updateUsageReport" + e);
        }
    }

    /**
     * 
     * @param mat 
     */
    public void deleteMaterial(BEMateriel mat) {
        try {
            dalusage.deleteMaterial(mat);
        } catch (SQLException ex) {
            System.out.println("Fejl i deleteMaterial i bllusage " + ex);
        }
    }

    /**
     * 
     * @param text 
     */
    public void addMaterial(String text) {
        try {
            dalusage.addMaterial(text);
        } catch (SQLException ex) {
            System.out.println("Fejl i addMaterial i bllUsage" + ex);
        }
    }

    /**
     * 
     * @param localAlarm
     * @return 
     */
    public ArrayList<BEUsage> getAllUsagesForAlarm(BEAlarm localAlarm) {
        ArrayList<BEUsage> allUsages = new ArrayList<>();
        for (BEUsage usage : getAllUsages()) {
            if (usage.getAlarm() == localAlarm) {
                allUsages.add(usage);
            }
        }
        return allUsages;
    }

    @Override
    public void create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
