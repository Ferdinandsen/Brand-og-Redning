package BLL;

import BE.BETime;
import BE.BEVehicle;
import DAL.DALTimelist;
import java.sql.SQLException;

/**
 *
 * @author Jacob
 */
public class BLLTimelist {

    DAL.DALTimelist daltime;
    private static BLLTimelist m_instance = null;

    private BLLTimelist() {
        try {
            daltime = DALTimelist.getInstance();
        } catch (SQLException e) {
            System.out.println("Fejl i BLLTimeList" + e);
        }
    }

    public static BLLTimelist getInstance() {
        if (m_instance == null) {
            m_instance = new BLLTimelist();
        }
        return m_instance;
    }

    public void sendToDAL(BETime time, BEVehicle odin, boolean hl, boolean ch, boolean st) {
//        daltime.updateTime(time, odin, hl, ch, st);
    }

}
