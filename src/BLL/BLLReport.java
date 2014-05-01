package BLL;

import BE.BEError;
import DAL.DALReport;
import java.sql.SQLException;

/**
 *
 * @author Shadowleet
 */
public class BLLReport {

    DAL.DALReport dalreport;
    private static BLLReport m_instance = null;
    
    private BLLReport(){
        try{
            dalreport = DALReport.getInstance();
        }
        catch (SQLException e){
            System.out.println("Fejl i report" + e);
        }
    }
    public static BLLReport getInstance(){
        if (m_instance == null){
            m_instance = new BLLReport();
        }
        return m_instance;
    }
 public void createErrorReport(BEError be) {
       try{
           dalreport.createErrorReport(be);
       }
       catch(SQLException e){
           System.out.println("Fejl i report" + e);
       }
    }
    
}
