package DAL;

import BE.BEEmployee;
import BE.BEFireman;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Team Kawabunga
 */
public class DALFireman {

    Connection m_connection;
    private static DALFireman m_instance = null;
    private ArrayList<BEFireman> allFiremen;
    DALEmployee dalEmployee = DALEmployee.getInstance();

    private DALFireman() throws SQLServerException, SQLException {
        m_connection = DBConnection.getInstance().getConnection();
        populateFiremen();
    }

    public static DALFireman getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALFireman();
        }
        return m_instance;
    }

    /**
     * Populates the ArrayList allFiremen
     *
     * @throws SQLException
     */
    public void populateFiremen() throws SQLException {
        allFiremen = new ArrayList<>();
        String sql = "SELECT * FROM Deltidsbrandmand";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();

        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int medarbejderRef = result.getInt("medarbejderRef");
            boolean holdleder = result.getBoolean("holdleder");
            boolean chauffør = result.getBoolean("chauffør");
            int team = result.getInt("team");
            BEEmployee localEmployee = null;

            for (BEEmployee employee : dalEmployee.getAllEmployees()) {
                if (employee.getMedarbejderNo() == medarbejderRef) {
                    localEmployee = employee;
                }
            }
            BEFireman fireman = new BEFireman(localEmployee, holdleder, chauffør, team);
            allFiremen.add(fireman);
        }
    }

    /**
     * returns the ArrayList
     *
     * @return allFiremen
     */
    public ArrayList<BEFireman> getAllFiremen() {
        return allFiremen;
    }

    /**
     * creates a firemen with roles and adds it to the database and the
     * ArrayList allFiremen
     *
     * @param emp - the selected employee
     * @param CH - Chauffør/driver
     * @param HL - Holdleder/teamleader
     * @param team - which team does he belong to
     * @throws SQLException
     */
    public void addEmployeeAsFireman(BEEmployee emp, boolean CH, boolean HL, int team) throws SQLException {
        String sql = "INSERT INTO Deltidsbrandmand VALUES (?,?,?,?,?) select @@identity";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, emp.getMedarbejderNo());
        ps.setBoolean(2, HL);
        ps.setBoolean(3, CH);
        ps.setBoolean(4, false);
        ps.setInt(5, team);
        ps.execute();

        BEFireman fireman = new BEFireman(emp, HL, CH, team);
        allFiremen.add(fireman);
    }

    /**
     * Delete a fireman from the deltidsbrandmand table and the ArrayList
     * allFiremen
     *
     * @param emp - specific employee
     * @throws SQLException
     */
    public void deleteFireman(BEEmployee emp) throws SQLException {

        String sql = "DELETE FROM Deltidsbrandmand WHERE medarbejderRef = ?";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, emp.getMedarbejderNo());
        ps.execute();
        BEFireman fireman = null;
        for (BEFireman f : getAllFiremen()) {
            if (f.getMedarbjeder().getMedarbejderNo() == emp.getMedarbejderNo()) {
                allFiremen.remove(fireman);
            }
        }
    }
}
