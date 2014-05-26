package BLL;

import BE.BEEmployee;
import BE.BEFireman;
import DAL.DALFireman;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Team Kawabunga
 */
public class BLLFireman implements IBLLType{ 

    DALFireman dalFireman;
    private static BLLFireman m_instance = null;

    private BLLFireman() {
        try {
            dalFireman = DALFireman.getInstance();
        } catch (SQLException e) {
            System.out.println("fejl i DALFireman " + e);
        }
    }

    /**
     * 
     * @return 
     */
    public static BLLFireman getInstance() {
        if (m_instance == null) {
            m_instance = new BLLFireman();
        }
        return m_instance;
    }

    /**
     * 
     */
    public void update() {
        try {
            dalFireman.populateFiremen();
        } catch (SQLException ex) {
            System.out.println("fejl i DALFireman " + ex);
        }
    }

    /**
     * 
     * @return 
     */
    public ArrayList<BEFireman> getAllfiremen() {
        return dalFireman.getAllFiremen();
    }

    /**
     * 
     * @return 
     */
    public int getHighestTeamNumber() {
        int number = 0;
        for (BEFireman fireman : getAllfiremen()) {
            if (fireman.getTeam() >= number) {
                number = fireman.getTeam();
            }
        }
        return number;
    }

    /**
     * 
     * @param emp 
     */
    public void deleteFireman(BEEmployee emp) {
        try {
            dalFireman.deleteFireman(emp);
        } catch (SQLException ex) {
            System.out.println("fejl i deleteFireman i bllFireman " + ex);
        }
    }

    public BEFireman getFiremanByMedabejderNo(BEEmployee emp, BEFireman fireman) {
        for (BEFireman fm : getAllfiremen()) {
            if (fm.getMedarbjeder().getMedarbejderNo() == emp.getMedarbejderNo()) {
                fireman = fm;
            }
        }
        return fireman;
    }   

    @Override
    public void create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
