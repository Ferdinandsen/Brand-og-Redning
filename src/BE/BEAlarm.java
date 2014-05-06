package BE;

import java.sql.Timestamp;

/**
 *
 * @author Team Kawabunga
 */
public class BEAlarm {

    private int EvaNo;
    private Timestamp time;
    private String desc;

    public BEAlarm(int eva, Timestamp time, String desc) {
        EvaNo = eva;
        this.time = time;
        this.desc = desc;
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
}
