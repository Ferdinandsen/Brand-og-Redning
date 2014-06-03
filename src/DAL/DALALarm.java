package DAL;

import BE.BEAlarm;
import BE.BEOdinAlarm;
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
        populateXMLAlarms();
        populateAlarm();
    }

    /**
     * 
     * @param tag
     * @param element
     * @return 
     */
    private String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);
        return node.getNodeValue();
    }

    /**
     *
     */
    private void populateXMLAlarms() {
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

    /**
     * returns the ArrayList allOdinAlarms
     *
     * @return allOdinAlarms
     */
    public ArrayList<BEOdinAlarm> getAllOdinAlarms() {
        return allOdinAlarms;
    }

    /**
     * The getInstance for DALAlarm (singleton)
     *
     * @return m_instance of the DALAlarm
     * @throws SQLException
     */
    public static DALALarm getInstance() throws SQLException {
        if (m_instance == null) {
            m_instance = new DALALarm();
        }
        return m_instance;
    }

    /**
     * Populates the allAlarm ArrayList from the database
     *
     * @throws SQLException
     */
    public void populateAlarm() throws SQLException {
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

    /**
     * returns allAlarms
     *
     * @return ArrayList allAlarms
     */
    public ArrayList<BEAlarm> getAllAlarms() {
        return allAlarms;
    }

    /**
     * Updates the alarm with the specific ID
     *
     * @param a - BEAlarm
     * @throws SQLException
     */
    public void updateAlarm(BEAlarm a) throws SQLException {
        String sql = "UPDATE Alarm SET alarmType = ?, gruppeNo = ?, detekterNo = ? WHERE id = ?";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setString(1, a.getAlarmType());
        ps.setInt(2, a.getGruppeNo());
        ps.setInt(3, a.getDetekterNo());
        ps.setInt(4, a.getId());
        ps.execute();
    }

    /**
     * Updates the hlbemærkning in the database
     *
     * @param alarm - BEAlarm
     * @throws SQLException
     */
    public void setHlBemærkning(BEAlarm alarm) throws SQLException {
        String sql = "UPDATE Alarm SET hlBemærkning = ?, hlGodkendtTid = ? WHERE id = ?";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setString(1, alarm.getHlBemærkning());
        ps.setTimestamp(2, alarm.getHlGodkendtTid());
        ps.setInt(3, alarm.getId());
        ps.execute();
    }

    /**
     * Updates the alarm with the specific ID
     *
     * @param alarm - BEAlarm
     * @param time Timestamp - the timestamp that needs to be put into the
     * database
     * @throws SQLException
     */
    public void confirmAlarm(BEAlarm alarm, Timestamp time) throws SQLException {
        String sql = "UPDATE Alarm SET done = ?, alarmType = ?, evaNo = ?, gruppeNo = ?, detekterNo = ?, ilBemærkning = ?, beskrivelse = ?, ilGodkendtTid = ? WHERE id = ?";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setBoolean(1, true);
        ps.setString(2, alarm.getAlarmType());
        ps.setInt(3, alarm.getEvaNo());
        ps.setInt(4, alarm.getGruppeNo());
        ps.setInt(5, alarm.getDetekterNo());
        ps.setString(6, alarm.getIlBemærkning());
        ps.setString(7, alarm.getDesc());
        ps.setTimestamp(8, time);
        ps.setInt(9, alarm.getId());
        ps.execute();
        alarm.setDone(true);
    }
}
