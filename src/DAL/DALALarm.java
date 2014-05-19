package DAL;

import BE.BEAlarm;
import BE.BEAppearance;
import BE.BEOdinAlarm;
import BE.BEUsage;
import BLL.BLLAlarm;
import BLL.BLLUsage;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Team Kawabunga
 */
public class DALALarm {

    private Connection m_connection;
    private static DALALarm m_instance = null;
    ArrayList<BEAlarm> allAlarms;
    ArrayList<BEOdinAlarm> allOdinAlarms;
    DALVehicle dalVehicle;

    private DALALarm() throws SQLServerException, SQLException {

        m_connection = DBConnection.getInstance().getConnection();
        dalVehicle = DALVehicle.getInstance();
        getXmlAlarms();
        populateAlarm();
    }

    private String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);
        return node.getNodeValue();
    }

    private void getXmlAlarms() {
        allOdinAlarms = new ArrayList<>();
        try {
            URL url = new URL("https://www.odin.dk/RSS/RSS.aspx?beredskabsID=2d58cb9b-3219-42f7-885d-3905cec3c40e");
            InputStream stream = url.openStream();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stream);
            doc.getDocumentElement().normalize();

            NodeList nodes = doc.getElementsByTagName("item");

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    BEOdinAlarm odinAlarm = new BEOdinAlarm(getValue("title", element), getValue("description", element), getValue("comments", element));
                    allOdinAlarms.add(odinAlarm);
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<BEOdinAlarm> getAllOdinAlarms() {
        return allOdinAlarms;
    }

    public static DALALarm getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALALarm();
        }
        return m_instance;
    }

    private void populateAlarm() throws SQLException {
        allAlarms = new ArrayList<>();
        String sql = "SELECT * FROM Alarm";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.execute();
        ResultSet result = ps.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            String title = result.getString("title");
            String desc = result.getString("beskrivelse");
            String station = result.getString("station");
            Timestamp tid = result.getTimestamp("tid");
            boolean done = result.getBoolean("done");
            String alarmType = result.getString("alarmType");
            int evaNo = result.getInt("evaNo");
            int gruppeNo = result.getInt("gruppeNo");
            int detekterNo = result.getInt("detekterNo");
            String hlBemærkning = result.getString("hlBemærkning");
            String ilBemærkning = result.getString("ilBemærkning");
            Timestamp ilGodkendtTid = result.getTimestamp("ilGodkendtTid");
            Timestamp hlGodkendtTid = result.getTimestamp("hlGodkendtTid");

            BEAlarm alarm = new BEAlarm(id, title, desc, station, tid, done, alarmType, evaNo, gruppeNo, detekterNo, hlBemærkning, ilBemærkning, ilGodkendtTid, hlGodkendtTid);
            allAlarms.add(alarm);
        }
    }

    public ArrayList<BEAlarm> getAllAlarms() {
        return allAlarms;
    }

//    public BEAlarm createAlarm(Date date, String fremmøde, String time) throws SQLException {
//        String sql = "INSERT INTO Alarm VALUES (?,?,?,?)select @@identity";
//
//        PreparedStatement ps = m_connection.prepareStatement(sql);
//        String[] parts = time.split(":");
//        int selectedHour = Integer.parseInt(parts[0]);
//        int selectedMin = Integer.parseInt(parts[1]);
//        Timestamp ts = new Timestamp(date.getYear(), date.getMonth(), date.getDate(), selectedHour, selectedMin, 0, 0);
//
//        ps.setString(1, null);
//        ps.setTimestamp(2, ts);
//        ps.setBoolean(3, false);
//        ps.setString(4, fremmøde);
//        ps.execute();
//        
//        ResultSet rs = ps.getGeneratedKeys();
//        int id = 0;
//        if (rs.next()){
//            id = rs.getInt(1);
//        }
//        BEAlarm alarm = new BEAlarm(id, 0, ts, fremmøde, false);
//        allAlarms.add(alarm);
//        return alarm;
//
//    }
    public void updateAlarm(BEAlarm a) throws SQLException {

        String sql = "UPDATE Alarm SET alarmType = ?, gruppeNo = ?, detekterNo = ? WHERE id = ?";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setString(1, a.getAlarmType());
        ps.setInt(2, a.getGruppeNo());
        ps.setInt(3, a.getDetekterNo());
        ps.setInt(4, a.getId());
        ps.execute();
    }

    public void setHlBemærkning(BEAlarm alarm) throws SQLException {
        String sql = "UPDATE Alarm SET hlBemærkning = ? WHERE id = ?";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setString(1, alarm.getHlBemærkning());
        ps.setInt(2, alarm.getId());
        ps.execute();
    }
}
