package DAL;

import BE.BEAction;
import BE.BEAlarm;
import BE.BEAppearance;
import BE.BELogin;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Team Kawabunga
 */
public class DALAction {

    private Connection m_connection;
    private static DALAction m_instance = null;
    ArrayList<BEAction> allActions;
    DALAppearance dalAppearance;
    DALALarm dalAlarm;
    DALLogin dalLogin;

    private DALAction() throws SQLServerException, SQLException {
        m_connection = DBConnection.getInstance().getConnection();
        dalAppearance = DALAppearance.getInstance();
        dalAlarm = DALALarm.getInstance();
        dalLogin = DALLogin.getInstance();
        populateAction();
    }

    public static DALAction getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALAction();
        }
        return m_instance;
    }

    private void populateAction() throws SQLException {
        allActions = new ArrayList<>();
        String sql = "SELECT * FROM Action";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();
        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int appearRef = result.getInt("appearanceRef");
            Timestamp tid = result.getTimestamp("godkendtTid");
            int loginRef = result.getInt("loginRef");
            String desc = result.getString("bemærkning");
            String alarmType = result.getString("alarmType");
            String gruppeNo = result.getString("gruppeNo");
            String detek = result.getString("detektorNo");
            int alarmRef = result.getInt("alarmRef");
            boolean ilGodkendt = result.getBoolean("ilGodkendt");

            BEAppearance localappear = null;

            for (BEAppearance appear : dalAppearance.getAppearances()) {
                if (appearRef == appear.getId()) {
                    localappear = appear;
                }
            }
            BELogin locallogin = null;
            for (BELogin login : dalLogin.getAllLogins()) {
                if (loginRef == login.getMedarbejder().getMedarbejderNo()) {
                    locallogin = login;
                }
            }
            BEAlarm localalarm = null;
            for (BEAlarm alarm : dalAlarm.getAllAlarms()){
                if(alarmRef == alarm.getId()){
                    localalarm = alarm;
                }
            }

            BEAction action = new BEAction(localappear, tid, locallogin, desc, alarmType, gruppeNo, detek, localalarm, ilGodkendt);
            allActions.add(action);
        }
    }
}
