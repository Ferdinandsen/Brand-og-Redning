package BLL;

import BE.BEAppearance;
import BE.BEVehicle;
import DAL.DALVehicle;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            System.out.println("fejl i update i bllVehicle " + ex);
        }
    }

    public void deleteVehicle(BEVehicle car) {
        try {
            dalvehicle.deleteVehicle(car);
        } catch (SQLException ex) {
            System.out.println("fejl i deletevehicle i bllvehicle " + ex);
        }
    }

    public void addVehicle(String desc, String bilNr, String model, String mærke, String nummerplade) {
        try {
            dalvehicle.addVehicle(desc, bilNr, model, mærke, nummerplade);
        } catch (SQLException ex) {
            System.out.println("fejl i addVehicle i bllvehicle " + ex);
        }
    }

    public ArrayList<BEVehicle> getAllVehiclesFromAppearances(ArrayList<BEAppearance> allHlGodkendtAppearances) {
        ArrayList<BEVehicle> vehicles = new ArrayList<>();
        for (BEAppearance app : allHlGodkendtAppearances) {
            if ( app.getVeh() != null && !vehicles.contains(app.getVeh())) {
                vehicles.add(app.getVeh());
            }
        }
        return vehicles;
    }
}
