package BLL;

import BE.BEError;
import BE.BEVehicle;
import DAL.DALReport;
import java.sql.SQLException;

/**
 *
 * @author Team Kawabunga
 */
public class BLLReport {

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
     * @return a new instance, if an instance isn't already created
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
}
