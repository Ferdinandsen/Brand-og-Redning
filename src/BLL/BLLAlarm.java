package BLL;

import BE.BEAlarm;
import DAL.DALALarm;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public ArrayList<BEAlarm> getAllAlarms() {
        return dalalarm.getAllAlarms();
    }

    public BEAlarm createAlarm(Date date, String time, String fremmøde) {
        try {
            return dalalarm.createAlarm(date, fremmøde, time);
        } catch (SQLException ex) {
            System.out.println("fejl i bllAlarm " + ex);
        }
        return null;
    }
}
