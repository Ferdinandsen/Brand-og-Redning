package BLL;

import BE.BEVehicle;
import DAL.DALVehicle;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jacob
 */
public class BLLVehicle {

    DAL.DALVehicle dalvehicle;
    private static BLLVehicle m_instance = null;

    private BLLVehicle() {
        try {
            dalvehicle = DALVehicle.getInstance();
        } catch (SQLException e) {
            System.out.println("SQL fejl i BLLKØTJ" + e);
        }
    }

    public static BLLVehicle getInstance() {
        if (m_instance == null) {
            m_instance = new BLLVehicle();
        }
        return m_instance;
    }

    public ArrayList<BEVehicle> GetVehicle() {
        return dalvehicle.getVehicles();
    }
}
