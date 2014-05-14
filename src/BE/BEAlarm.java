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

    public BEAlarm(int id, String title, String desc, String station, Timestamp time, boolean done) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.station = station;
        this.time = time;
        this.done = done;
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
    public String getTimeString(){
        String alarm[] = time.toString().split(" ");
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
        return getDesc() + " - " + getTimeString();
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

}
