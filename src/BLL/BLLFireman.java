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

    /**
     *
     * @return a new instance, if an instance isn't already created
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
     * @return all firemen
     */
    public ArrayList<BEFireman> getAllfiremen() {
        return dalFireman.getAllFiremen();
    }

    /**
     *
     * @return the highest number of the teams
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

    /**
     *
     * @param emp
     * @param fireman
     * @return the fireman, with the same employee number as emp
     */
    public BEFireman getFiremanByMedabejderNo(BEEmployee emp, BEFireman fireman) {
        for (BEFireman fm : getAllfiremen()) {
            if (fm.getMedarbjeder().getMedarbejderNo() == emp.getMedarbejderNo()) {
                fireman = fm;
            }
        }
        return fireman;
    }
}
