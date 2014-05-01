package BLL;

import BE.BEUsage;
import DAL.DALUsage;
import java.sql.SQLException;

/**
 *
 * @author Jacob
 */
public class BLLUsage {

    DALUsage dalusage;
    private static BLLUsage m_instance = null;

    private BLLUsage() {
        try {
            dalusage = DALUsage.getInstance();
        } catch (SQLException e) {
            System.out.println("Fejl i BLLKØTJ " + e);
        }
    }

    public static BLLUsage getInstance() {
        if (m_instance == null) {
            m_instance = new BLLUsage();
        }
        return m_instance;
    }
    
    public void createReport(BEUsage u){
        
    }
}
