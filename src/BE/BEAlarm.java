package BE;

import java.sql.Timestamp;

/**
 *
 * @author Team Kawabunga
 */
public class BEAlarm {

    private int id;
    private int EvaNo;
    private Timestamp time;
    private String desc;
    private boolean done;

    public BEAlarm(int id, int eva, Timestamp time, String desc, boolean done) {
        this.id = id;
        EvaNo = eva;
        this.time = time;
        this.desc = desc;
        this.done = done;
    }

    /**
     * @return the EvaNo
     */
    public int getEvaNo() {
        return EvaNo;
    }

    /**
     * @param EvaNo the EvaNo to set
     */
    public void setEvaNo(int EvaNo) {
        this.EvaNo = EvaNo;
    }

    /**
     * @return the time
     */
    public Timestamp getTime() {
        return time;
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
        return "" + getEvaNo();
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
}
