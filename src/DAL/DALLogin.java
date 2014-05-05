package DAL;

import BE.BEAddress;
import BE.BEEmployee;
import BE.BELogin;
import BE.BEZipCode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Team Kawabunga
 */
public class DALLogin {

    Connection m_connection;
    private static DALLogin m_instance = null;
    private ArrayList<BEZipCode> allZipCodes = new ArrayList<>();
    private ArrayList<BEAddress> allAddresses = new ArrayList<>();
    private ArrayList<BEEmployee> allEmployees = new ArrayList<>();
    private ArrayList<BELogin> allLogins = new ArrayList<>();

    private DALLogin() throws SQLException {
        m_connection = DBConnection.getInstance().getConnection();
        populateZip();
        populateAddress();
        populateEmployee();
        populateLogins();

    }

    public static DALLogin getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALLogin();
        }
        return m_instance;
    }

    private void populateEmployee() throws SQLException {
        String sql = "SELECT * FROM Medarbejder";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();
        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int medarbejderNo = result.getInt("medarbejderNo");
            String fornavn = result.getString("fornavn");
            String mellemnavn = result.getString("mellemnavn");
            if (mellemnavn == (null)) {
                mellemnavn = "";
            }
            String efternavn = result.getString("efternavn");
            String CPR = result.getString("CPR");
            String portræt = result.getString("portræt");
            int adresseRef = result.getInt("adresseRef");
            boolean isFriviligBrandmand = result.getBoolean("isFriviligBrandmand");

            BEAddress localaddress = null;

            for (BEAddress address : getAllAddresses()) {
                if (adresseRef == address.getId()) {
                    localaddress = address;
                }
                BEEmployee employee = new BEEmployee(medarbejderNo, fornavn, mellemnavn, efternavn, CPR, portræt, localaddress, isFriviligBrandmand);
                allEmployees.add(employee);
            }
        }
    }

    private void populateAddress() throws SQLException {
        String sql = "SELECT * FROM Adresse";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();
        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int ID = result.getInt("id");
            String gadenavn = result.getString("gadenavn");
            int gadenummer = result.getInt("gadenummer");
            int etage = result.getInt("etage");
            String lejlighed = result.getString("lejlighed");
            int postnummerRef = result.getInt("postnummerRef");

            BEZipCode localZipCode = null;

            for (BEZipCode zipCode : getAllZipCodes()) {
                if (postnummerRef == zipCode.getZIPCODE()) {
                    localZipCode = zipCode;
                }
                BEAddress address = new BEAddress(ID, gadenavn, gadenummer, etage, lejlighed, localZipCode);
                getAllAddresses().add(address);
            }
        }
    }

    private void populateZip() throws SQLException {
        String sql = "SELECT * FROM Postnummer";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();
        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int postNummer = result.getInt("postnummer");
            String bynavn = result.getString("bynavn");

            BEZipCode zipCode = new BEZipCode(postNummer, bynavn);
            getAllZipCodes().add(zipCode);
        }
    }

    /**
     * @return the allEmployess
     */
    public ArrayList<BEEmployee> getAllEmployees() {
        return allEmployees;
    }

    /**
     * @return the allZipCodes
     */
    public ArrayList<BEZipCode> getAllZipCodes() {
        return allZipCodes;
    }

    /**
     * @return the allAddresses
     */
    public ArrayList<BEAddress> getAllAddresses() {
        return allAddresses;
    }

    public ArrayList<BELogin> getAllLogins() {
        return allLogins;
    }

    public boolean doesUserExist() throws SQLException {
        String sql = "SELECT * FROM Login";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();
        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int postNummer = result.getInt("postnummer");
            String bynavn = result.getString("bynavn");

            BEZipCode zipCode = new BEZipCode(postNummer, bynavn);
            getAllZipCodes().add(zipCode);
        }
        return false;

    }

    private void populateLogins() throws SQLException {
        String sql = "SELECT * FROM Login";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();
        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int medarbejderRef = result.getInt("medarbejderRef");
            String kode = result.getString("kode");
            boolean admin = result.getBoolean("admin");
            boolean kontor = result.getBoolean("kontor");
            boolean holdleder = result.getBoolean("holdleder");

            BEEmployee localEmployee = null;
            for (BEEmployee emp : getAllEmployees()){
                if (medarbejderRef == emp.getMedarbejderNo()){
                    localEmployee = emp;
                }
            }
            BELogin login = new BELogin(localEmployee, kode, admin, kontor, holdleder);
            allLogins.add(login);
        }
    }
}