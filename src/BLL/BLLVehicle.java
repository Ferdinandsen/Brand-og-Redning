package BLL;

import BE.BEVehicle;
import DAL.DALVehicle;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Team Kawabunga
 */
public class BLLVehicle {

    DAL.DALVehicle dalvehicle;
    private static BLLVehicle m_instance = null;

    private BLLVehicle() {
        try {
            dalvehicle = DALVehicle.getInstance();
        } catch (SQLException e) {
            System.out.println("Fejl i BLLKØTJ " + e);
        }
    }

    public static BLLVehicle getInstance() {
        if (m_instance == null) {
            m_instance = new BLLVehicle();
        }
        return m_instance;
    }

    public ArrayList<BEVehicle> getAllVehicles() {
        return dalvehicle.getVehicles();
    }

    public void update() {
        try {
            dalvehicle.populateVehicle();
        } catch (SQLException ex) {
            System.out.println("blabla" + ex);
        }
    }
}
