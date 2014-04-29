package BLL;

import BE.BEFireman;
import DAL.DALFireman;
import GUI.CheckInView;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakob
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

    public ArrayList<BEFireman> getAllfiremen() {
        return dalFireman.getAllFiremen();
    }

    public void changeBit(BEFireman fireman) {
        try {
            dalFireman.changeBit(fireman);
        } catch (SQLException ex) {
            System.out.println("kunne ikke changeBit" + ex);
        }
    }

    public void createCheckInTimestamp(BEFireman fireman) {
        try {
            dalFireman.createCheckInTimestamp(fireman);
        } catch (SQLException ex) {
            System.out.println("kunne ikke lave timestamp" + ex);
        }
    }

    public void createCheckOutTimestamp(BEFireman fireman) {
      try {
            dalFireman.createCheckOutTimestamp(fireman);
        } catch (SQLException ex) {
            System.out.println("kunne ikke lave timestamp" + ex);
        }}
}
