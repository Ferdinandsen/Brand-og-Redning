package BLL;

import BE.BEMateriel;
import BE.BEUsage;
import DAL.DALUsage;
import java.sql.SQLException;
import java.util.ArrayList;

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
        return dalusage.getMateriel();
    }
}
