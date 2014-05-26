package BLL;

import BE.BEError;
import BE.BEVehicle;
import DAL.DALReport;
import java.sql.SQLException;

/**
 *
 * @author Team Kawabunga
 */
public class BLLReport implements IBLLType{

    DAL.DALReport dalreport;
    private static BLLReport m_instance = null;

    private BLLReport() {
        try {
            dalreport = DALReport.getInstance();
        } catch (SQLException e) {
            System.out.println("Fejl i report" + e);
        }
    }

    /**
     * 
     * @return 
     */
    public static BLLReport getInstance() {
        if (m_instance == null) {
            m_instance = new BLLReport();
        }
        return m_instance;
    }

    /**
     * 
     * @param be
     * @param veh 
     */
    public void createErrorReport(BEError be, BEVehicle veh) {
        try {
            dalreport.createErrorReport(be, veh);
        } catch (SQLException e) {
            System.out.println("Fejl i report" + e);
        }
    }

    @Override
    public void create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
