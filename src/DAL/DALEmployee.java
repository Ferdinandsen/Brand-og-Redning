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
 * @author André
 */
public class DALEmployee {

    Connection m_connection;
    private static DALEmployee m_instance = null;
    private ArrayList<BEZipCode> allZipCodes;
    private ArrayList<BEAddress> allAddresses;
    private ArrayList<BEEmployee> allEmployess;

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

    public void populateEmployee() throws SQLException {
        allEmployess = new ArrayList<>();
        String sql = "select * from Medarbejder";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();
        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int medarbejderNo = result.getInt("medarbejderNo");
            String fornavn = result.getString("fornavn");
            String mellemnavn = result.getString("mellemnavn");
            String efternavn = result.getString("efternavn");
            String CPR = result.getString("CPR");
            String portræt = result.getString("portræt");
            int adresseRef = result.getInt("adresseRef");
            boolean isFriviligBrandmand = result.getBoolean("isFriviligBrandmand");

            BEAddress localaddress = null;

            for (BEAddress address : allAddresses) {
                if (adresseRef == address.getId()) {
                    localaddress = address;
                }
                BEEmployee employee = new BEEmployee(medarbejderNo, fornavn, mellemnavn, efternavn, CPR, portræt, localaddress, isFriviligBrandmand);
                allEmployess.add(employee);
            }
        }
    }

    private void populateAddress() throws SQLException {
        allEmployess = new ArrayList<>();
        String sql = "select * from Address";

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

            for (BEZipCode zipCode : allZipCodes) {
                if (postnummerRef == zipCode.getZIPCODE()) {
                    localZipCode = zipCode;
                }
                BEAddress address = new BEAddress(ID, gadenavn, gadenummer, etage, lejlighed, localZipCode);
                allAddresses.add(address);
            }
        }
    }

    private void populateZip() throws SQLException {
        allEmployess = new ArrayList<>();
        String sql = "select * from PostNu";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();
        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int postNummer = result.getInt("postnummer");
            String bynavn = result.getString("bynavn");

            BEZipCode zipCode = new BEZipCode(postNummer, bynavn);
            allZipCodes.add(zipCode);
        }
    }
}
