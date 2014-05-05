package BLL;

import BE.BEAddress;
import BE.BEAlarm;
import DAL.DALALarm;
import java.sql.SQLException;
import java.util.ArrayList;

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
        } catch (SQLException e) {
            System.out.println("fejl i bllEmployee " + e);
        }
    }

    public static BLLAlarm getInstance() {
        if (m_instance == null) {
            m_instance = new BLLAlarm();
        }
        return m_instance;
    }
    
    public ArrayList<BEAlarm> getAllAlarms () {
        return dalalarm.getAllAlarms();
    }
}
