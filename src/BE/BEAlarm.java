package BE;

import java.awt.Color;
import java.sql.Timestamp;

/**
 *
 * @author Team Kawabunga
 */
public class BEAlarm {

    private int id;
    private String title;
    private String desc;
    private String station;
    private Timestamp time;
    private boolean done;
    private String alarmType;
    private int evaNo;
    private int gruppeNo;
    private int detekterNo;
    private String hlBemærkning;
    private String ilBemærkning;
    private Timestamp ilGodkendtTid;
    private Timestamp hlGodkendtTid;
    private boolean guestConfirmed;

    public BEAlarm(int id, String title, String desc, String station, Timestamp time, boolean done,
            String alarmType, int evaNo, int gruppeNo, int detekterNo, String hlBemærkning,
            String ilBemærkning, Timestamp ilGodkendtTid, Timestamp hlGodkendtTid) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.station = station;
        this.time = time;
        this.done = done;
        this.alarmType = alarmType;
        this.evaNo = evaNo;
        this.gruppeNo = gruppeNo;
        this.detekterNo = detekterNo;
        this.hlBemærkning = hlBemærkning;
        this.ilBemærkning = ilBemærkning;
        this.ilGodkendtTid = ilGodkendtTid;
        this.hlGodkendtTid = hlGodkendtTid;
    }

    /**
     * @return the time
     */
    public Timestamp getTime() {
        return time;
    }

    public Color getColor() {
        if (guestConfirmed) {
            return Color.ORANGE;
        } else {
            return Color.GREEN;
        }
    }

    public void setGuestConfirmed(boolean value) {
        guestConfirmed = value;
    }

    public boolean hasGuestConfirmed() {
        return guestConfirmed;
    }

    /**
     *
     * @return the date as string
     */
    public String getDateString() {
        String alarm[] = getTime().toString().split(" ");
        return alarm[0];
    }

    /**
     *
     * @return a time as a string
     */
    public String getTimeString() {
        String alarm[] = getTime().toString().split(" ");
        String[] fixedAlarm1 = alarm[1].split(":");
        String finalAlarm = fixedAlarm1[0] + ":" + fixedAlarm1[1];
        return finalAlarm;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return getDesc(); //+ " - " + getTime();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the done
     */
    public boolean isDone() {
        return done;
    }

    /**
     * @param done the done to set
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the station
     */
    public String getStation() {
        return station;
    }

    /**
     * @param station the station to set
     */
    public void setStation(String station) {
        this.station = station;
    }

    /**
     * @return the alarmType
     */
    public String getAlarmType() {
        return alarmType;
    }

    /**
     * @param alarmType the alarmType to set
     */
    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    /**
     * @return the evaNo
     */
    public int getEvaNo() {
        return evaNo;
    }

    /**
     * @param evaNo the evaNo to set
     */
    public void setEvaNo(int evaNo) {
        this.evaNo = evaNo;
    }

    /**
     * @return the gruppeNo
     */
    public int getGruppeNo() {
        return gruppeNo;
    }

    /**
     * @param gruppeNo the gruppeNo to set
     */
    public void setGruppeNo(int gruppeNo) {
        this.gruppeNo = gruppeNo;
    }

    /**
     * @return the detekterNo
     */
    public int getDetekterNo() {
        return detekterNo;
    }

    /**
     * @param detekterNo the detekterNo to set
     */
    public void setDetekterNo(int detekterNo) {
        this.detekterNo = detekterNo;
    }

    /**
     * @return the hlBemærkning
     */
    public String getHlBemærkning() {
        return hlBemærkning;
    }

    /**
     * @param hlBemærkning the hlBemærkning to set
     */
    public void setHlBemærkning(String hlBemærkning) {
        this.hlBemærkning = hlBemærkning;
    }

    /**
     * @return the ilBemærkning
     */
    public String getIlBemærkning() {
        return ilBemærkning;
    }

    /**
     * @param ilBemærkning the ilBemærkning to set
     */
    public void setIlBemærkning(String ilBemærkning) {
        this.ilBemærkning = ilBemærkning;
    }

    /**
     * @return the ilGodkendtTid
     */
    public Timestamp getIlGodkendtTid() {
        return ilGodkendtTid;
    }

    /**
     *
     * @return
     */
    public String getIlGodkendtTidTimeString() {
        String godkendtTid[] = ilGodkendtTid.toString().split(" ");
        String[] fixedGodkendtTid = godkendtTid[1].split(":");
        String finalGodkendtTid = fixedGodkendtTid[0] + ":" + fixedGodkendtTid[1];
        return finalGodkendtTid;
    }

    /**
     * @param ilGodkendtTid the ilGodkendtTid to set
     */
    public void setIlGodkendtTid(Timestamp ilGodkendtTid) {
        this.ilGodkendtTid = ilGodkendtTid;
    }

    /**
     * @return the hlGodkendtTid
     */
    public Timestamp getHlGodkendtTid() {
        return hlGodkendtTid;
    }

    /**
     * @param hlGodkendtTid the hlGodkendtTid to set
     */
    public void setHlGodkendtTid(Timestamp hlGodkendtTid) {
        this.hlGodkendtTid = hlGodkendtTid;
    }
}
