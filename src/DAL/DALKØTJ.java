package DAL;

import BE.BEKØTJ;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jacob
 */
public class DALKØTJ {

    /**
     * @return the m_instance
     */
    public static DALKØTJ getM_instance() {
        return m_instance;
    }

    /**
     * @param aM_instance the m_instance to set
     */
    public static void setM_instance(DALKØTJ aM_instance) {
        m_instance = aM_instance;
    }

    private Connection m_connection;
    private static DALKØTJ m_instance = null;
    private ArrayList<BEKØTJ> køtj = new ArrayList<>();

    private DALKØTJ() throws SQLException, SQLServerException {
        m_connection = DBConnection.getInstance().getConnection();
        populateKØTJ();
    }

    public static DALKØTJ getInstance() throws SQLException {
        if (getM_instance() == null) {
            setM_instance(new DALKØTJ());
        }
        return getM_instance();
    }

    private void populateKØTJ() throws SQLException {
        String sql = "select * from KØTJ order by odinNo";

        PreparedStatement ps = getM_connection().prepareStatement(sql);
        ps.execute();

        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int odin = result.getInt("odinNo");
            String reg = result.getString("registrering");
            String mark = result.getString("mærke");
            String mod = result.getString("model");
            String desc = result.getString("beskrivelse");

            BEKØTJ bil = new BEKØTJ(odin, reg, mark, mod, desc);
            getKøtj().add(bil);
        }
    }

    /**
     * @return the m_connection
     */
    public Connection getM_connection() {
        return m_connection;
    }

    /**
     * @param m_connection the m_connection to set
     */
    public void setM_connection(Connection m_connection) {
        this.m_connection = m_connection;
    }

    /**
     * @return the køtj
     */
    public ArrayList<BEKØTJ> getKøtj() {
        return køtj;
    }

    /**
     * @param køtj the køtj to set
     */
    public void setKøtj(ArrayList<BEKØTJ> køtj) {
        this.køtj = køtj;
    }

}
