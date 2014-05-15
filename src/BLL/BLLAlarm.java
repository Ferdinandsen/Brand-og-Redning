package BLL;

import BE.BEAlarm;
import BE.BEOdinAlarm;
import DAL.DALALarm;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Team Kawabunga
 */
public class BLLAlarm {

    private static BLLAlarm m_instance = null;
    DALALarm dalalarm;

    private BLLAlarm() {
        try {
            dalalarm = DALALarm.getInstance();
        
        } catch (SQLException ex) {
            System.out.println("fejl i dalAlarm: " + ex.getMessage());
        }
    }
    

    public static BLLAlarm getInstance() {
        if (m_instance == null) {
            m_instance = new BLLAlarm();
        }
        return m_instance;
    }

    public ArrayList<BEAlarm> getAllAlarms() {
        return dalalarm.getAllAlarms();
    }
    public ArrayList<BEOdinAlarm> getAllOdinAlarms(){
        return dalalarm.getAllOdinAlarms();
    }

//    public BEAlarm createAlarm(Date date, String time, String fremmøde) {
//        try {
//            return dalalarm.createAlarm(date, fremmøde, time);
//        } catch (SQLException ex) {
//            System.out.println("fejl i bllAlarm " + ex);
//        }
//        return null;
//    }
}
