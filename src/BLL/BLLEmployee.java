package BLL;

import BE.BEEmployee;
import BE.BELogin;
import DAL.DALEmployee;
import DAL.DALFireman;
import DAL.DALLogin;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Team Kawabunga
 */
public class BLLEmployee {

    DALEmployee dalEmployee;
    DALLogin dalLogin;
    DALFireman dalFireman;
    private static BLLEmployee m_instance = null;

    private BLLEmployee() {
        try {
            dalEmployee = DALEmployee.getInstance();
            dalLogin = DALLogin.getInstance();
            dalFireman = DALFireman.getInstance();
        } catch (SQLException e) {
            System.out.println("fejl i bllEmployee " + e);
        }
    }

    /**
     *
     * @return
     */
    public static BLLEmployee getInstance() {
        if (m_instance == null) {
            m_instance = new BLLEmployee();
        }
        return m_instance;
    }

    /**
     *
     * @param name
     * @param password
     * @return
     */
    public boolean doesUserExist(String name, char[] password) {
        for (BELogin login : dalLogin.getAllLogins()) {
            if (login.getMedarbejder().getFornavn().trim().equalsIgnoreCase(name.trim()) && isPasswordCorrect(password, login)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param password
     * @param login
     * @return
     */
    private boolean isPasswordCorrect(char[] password, BELogin login) {
        boolean isCorrect;
        char[] correctPassword = login.getKode().toCharArray();

        if (password.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals(password, correctPassword);
        }
        //Fjerner koden fra hukommelsen, så man ikke kan dumpe den
        Arrays.fill(correctPassword, '0');

        return isCorrect;
    }

    /**
     *
     * @param name
     * @return
     */
    public BELogin getLogin(String name) {
        for (BELogin login : dalLogin.getAllLogins()) {
            if (name.trim().equalsIgnoreCase(login.getMedarbejder().getFornavn().trim())) {
                return login;
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<BEEmployee> getAllEmployees() {
        return dalEmployee.getAllEmployees();
    }

    /**
     *
     * @param gadenavn
     * @param gadenummer
     * @param etage
     * @param lejlighed
     * @param postnummer
     * @return
     */
    public int addAddress(String gadenavn, int gadenummer, int etage, String lejlighed, int postnummer) {
        try {
            return dalEmployee.addAddress(gadenavn, gadenummer, etage, lejlighed, postnummer);
        } catch (SQLException ex) {
            System.out.println("fejl i addAddress i bllEmployee " + ex);
        }
        return 0;
    }

    /**
     *
     * @param fornavn
     * @param mellemnavn
     * @param efternavn
     * @param CPR
     * @param portræt
     * @param isFireman
     * @param adresseID
     * @param CH
     * @param HL
     * @param team
     */
    public void addEmployee(String fornavn, String mellemnavn, String efternavn, String CPR, String portræt, boolean isFireman, int adresseID, boolean CH, boolean HL, int team) {

        try {
            BEEmployee emp = dalEmployee.addEmployee(fornavn, mellemnavn, efternavn, CPR, portræt, isFireman, adresseID);
            if (isFireman) {
                dalFireman.addEmployeeAsFireman(emp, CH, HL, team);
            }
        } catch (SQLException ex) {
            System.out.println("fejl i addEmployee i bllEmployee " + ex);
        }
    }

    /**
     *
     * @param emp
     */
    public void deleteEmployee(BEEmployee emp) {
        try {
            dalEmployee.deleteEmployee(emp);
        } catch (SQLException ex) {
            System.out.println("fejl i delete i bllEmployee " + ex);
        }
    }
}
