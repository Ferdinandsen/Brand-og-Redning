package BLL;

import BE.BEAlarm;
import BE.BEAlarmKøtj;
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
    public ArrayList<BEAlarmKøtj> getAllAlarmKøtj(){
        return dalalarm.getAllAlarmKøtj();
    }
    public ArrayList<BEAlarmKøtj> getAllApprovedAlarmKøtj(){
        ArrayList<BEAlarmKøtj> test = new ArrayList<>();
        for(BEAlarmKøtj be : getAllAlarmKøtj()){
            if (!be.isIsConfirmed()){
                test.add(be);
            }
        }
        return test;
    }

    public void createAlarm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
