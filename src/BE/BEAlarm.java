package BE;

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
    private BEUsage forbrug;
    private String alarmType;
    private int evaNo;
    private int gruppeNo;
    private int detekterNo;

    public BEAlarm(int id, String title, String desc, String station, Timestamp time, boolean done, BEUsage forbrug, String alarmType, int evaNo, int gruppeNo, int detekterNo) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.station = station;
        this.time = time;
        this.done = done;
        this.forbrug = forbrug;
        this.alarmType = alarmType;
        this.evaNo = evaNo;
        this.gruppeNo = gruppeNo;
        this.detekterNo = detekterNo;
    }
   

    /**
     * @return the EvaNo
     */
    /**
     * @return the time
     */
    public Timestamp getTime() {
        return time;
    }

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
        return getDesc(); //+ " - " + getTimeString();
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
     * @return the forbrug
     */
    public BEUsage getForbrug() {
        return forbrug;
    }

    /**
     * @param forbrug the forbrug to set
     */
    public void setForbrug(BEUsage forbrug) {
        this.forbrug = forbrug;
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
     * @param timeString the timeString to set
     */

}
