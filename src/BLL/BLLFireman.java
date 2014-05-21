package BLL;

import BE.BEEmployee;
import BE.BEFireman;
import DAL.DALFireman;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Team Kawabunga
 */
public class BLLFireman {

    DALFireman dalFireman;
    private static BLLFireman m_instance = null;

    private BLLFireman() {
        try {
            dalFireman = DALFireman.getInstance();
        } catch (SQLException e) {
            System.out.println("fejl i DALFireman " + e);
        }
    }

    public static BLLFireman getInstance() {
        if (m_instance == null) {
            m_instance = new BLLFireman();
        }
        return m_instance;
    }

    public void update() {
        try {
            dalFireman.populateFiremen();
        } catch (SQLException ex) {
            System.out.println("fejl i DALFireman " + ex);
        }
    }

    public ArrayList<BEFireman> getAllfiremen() {
        return dalFireman.getAllFiremen();
    }

//    public void changeBit(BEFireman fireman) {
//        try {
//            dalFireman.changeBit(fireman);
//        } catch (SQLException ex) {
//            System.out.println("kunne ikke changeBit" + ex);
//        }
//    }
    public int getHighestTeamNumber() {
        int number = 0;
        for (BEFireman fireman : getAllfiremen()) {
            if (fireman.getTeam() >= number) {
                number = fireman.getTeam();
            }
        }
        return number;
    }

    public void deleteFireman(BEEmployee emp) {
        try {
            dalFireman.deleteFireman(emp);
        } catch (SQLException ex) {
            System.out.println("fejl i deleteFireman i bllFireman " + ex);
        }
    }
}
