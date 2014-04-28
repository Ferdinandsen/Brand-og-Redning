package DAL;

import BE.BEFiremanTest;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Firemen {

    private ArrayList<BEFiremanTest> allFiremen;
    
    Connection m_connection;
    private static Firemen m_instance = null;

    private Firemen() throws SQLServerException {
        m_connection = DBConnection.getInstance().getConnection();
    
    }
    public static Firemen getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new Firemen();
        }
        return m_instance;
    }

    public ArrayList<BEFiremanTest> getFiremen() {
        return allFiremen;
    }

    public void populateFiremen() throws SQLException {
        allFiremen = new ArrayList<>();
        String sql = "select * from Deltidsbrandmand";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();
        ResultSet result = ps.getResultSet();
        while (result.next()) {
            String name = "TestName";
            boolean isHoldLeder = result.getBoolean("holdleder");
            boolean isChauffør = result.getBoolean("chauffør");
            boolean isCheckIn = result.getBoolean("isCheckIn");

            BEFiremanTest fireman = new BEFiremanTest(name, isHoldLeder, isChauffør, isCheckIn);
            allFiremen.add(fireman);
            System.out.println(fireman.isCheckedIn());
        }
    }
}
