package DAL;

import BE.BEAddress;
import BE.BEEmployee;
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
public class DALEmployee {

    Connection m_connection;
    private static DALEmployee m_instance = null;
    private ArrayList<BEZipCode> allZipCodes = new ArrayList<>();
    private ArrayList<BEAddress> allAddresses = new ArrayList<>();
    private ArrayList<BEEmployee> allEmployees = new ArrayList<>();

    private DALEmployee() throws SQLException {
        m_connection = DBConnection.getInstance().getConnection();
        populateZip();
        populateAddress();
        populateEmployee();
    }

    public static DALEmployee getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALEmployee();
        }
        return m_instance;
    }

    private void populateEmployee() throws SQLException {
        allEmployees = new ArrayList<>();
        String sql = "select * from Medarbejder order by case when fornavn = 'Gæst' then 0 else 1 end, fornavn";

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
            }
            BEEmployee employee = new BEEmployee(medarbejderNo, fornavn, mellemnavn, efternavn, CPR, portræt, localaddress, isFriviligBrandmand);
            allEmployees.add(employee);
        }
    }

    private void populateAddress() throws SQLException {
        allAddresses = new ArrayList<>();
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
                if (postnummerRef == zipCode.getZipCode()) {
                    localZipCode = zipCode;
                }
                BEAddress address = new BEAddress(ID, gadenavn, gadenummer, etage, lejlighed, localZipCode);
                getAllAddresses().add(address);
            }
        }
    }

    private void populateZip() throws SQLException {
        allZipCodes = new ArrayList<>();
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

    public int addAddress(String gadenavn, int gadenummer, int etage, String lejlighed, int postnummer) throws SQLException {
        String sql = "INSERT INTO Adresse VALUES (?,?,?,?,?) select @@identity";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setString(1, gadenavn);
        ps.setInt(2, gadenummer);
        ps.setInt(3, etage);
        ps.setString(4, lejlighed);
        ps.setInt(5, postnummer);
        ps.execute();

        ResultSet rs = ps.getGeneratedKeys();
        int id = 0;
        if (rs.next()) {
            id = rs.getInt(1);
        }
        BEZipCode localZip = null;
        for (BEZipCode zip : getAllZipCodes()){
            if (zip.getZipCode() == postnummer){
                localZip = zip;
            }
        }
        BEAddress add = new BEAddress(id, gadenavn, gadenummer, etage, lejlighed, localZip);
        allAddresses.add(add);
        return id;
    }

    public BEEmployee addEmployee(String fornavn, String mellemnavn, String efternavn, String CPR, String portræt, boolean fireman, int adresseID) throws SQLException {
        String sql = "INSERT INTO Medarbejder VALUES (?,?,?,?,?,?,?) select @@identity";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setString(1, fornavn);
        ps.setString(2, mellemnavn);
        ps.setString(3, efternavn);
        ps.setString(4, CPR);
        ps.setString(5, portræt);
        ps.setInt(6, adresseID);
        ps.setBoolean(7, fireman);
        ps.execute();
        
        ResultSet rs = ps.getGeneratedKeys();
        int id = 0;
        if (rs.next()) {
            id = rs.getInt(1);
        }
       
        BEAddress localAddress = null;
        for (BEAddress add : getAllAddresses()){
            if (add.getId() == adresseID){
                localAddress = add;
            }
        }
        
        BEEmployee employee = new BEEmployee(id, fornavn, mellemnavn, efternavn, CPR, portræt, localAddress, fireman);
        allEmployees.add(employee);
        return employee;
    }

    public void deleteEmployee(BEEmployee emp) throws SQLException {
        String sql = "DELETE FROM Medarbejder WHERE medarbejderNo = ?";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, emp.getMedarbejderNo());
        ps.execute();
        allEmployees.remove(emp);
    }
}
