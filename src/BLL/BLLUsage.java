package BLL;

import BE.BEMateriel;
import BE.BEUsage;
import DAL.DALUsage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Team Kawabunga
 */
public class BLLUsage {

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
    public ArrayList<BEUsage> getAllUsages(){
        return dalusage.getAllUsages();
    }

    public static BLLUsage getInstance() {
        if (m_instance == null) {
            m_instance = new BLLUsage();
        }
        return m_instance;
    }

    public void createReport(BEUsage u) {
        try {
            dalusage.createUsageReport(u);

        } catch (SQLException ex) {
            System.out.println("Fejl i BLLUsage createReport " + ex);
        }
    }

    public ArrayList<BEMateriel> getAllMats() {
        return dalusage.getAllMats();
    }

    public void updateUsageReport(BEUsage bu) {
       try{
           dalusage.updateUsageReport(bu);
       }
       catch (SQLException e){
           System.out.println("Fejl i updateUsageReport" + e);
       }
    }

    public void deleteMaterial(BEMateriel mat) {
        try {
            dalusage.deleteMaterial(mat);
        } catch (SQLException ex) {
            System.out.println("Fejl i deleteMaterial i bllusage " + ex);
        }
    }

    public void addMaterial(String text) {
        try {
            dalusage.addMaterial(text);
        } catch (SQLException ex) {
             System.out.println("Fejl i addMaterial i bllUsage" + ex);
        }
    }
}
