package BE;

import java.sql.Timestamp;

/**
 *
 * @author Team Kawabunga
 */
public class BEAction {

    private BEAppearance appearance;
    private Timestamp godkendtTid;
    private BELogin login;
    private String bemærkninger;
    private String alarmType;
    private String gruppeNo;
    private String detektorNo;
    private BEAlarm alarm;
    private boolean ilGodkendt;

    public BEAction(BEAppearance appearance, Timestamp godkendtTid,
            BELogin login, String bemærkninger, String alarmType,
            String gruppNo, String detektorNo, BEAlarm alarm, boolean ilGodkendt) {
        this.appearance = appearance;
        this.godkendtTid = godkendtTid;
        this.login = login;
        this.bemærkninger = bemærkninger;
        this.alarmType = alarmType;
        this.gruppeNo = gruppNo;
        this.detektorNo = detektorNo;
        this.alarm = alarm;
        this.ilGodkendt = ilGodkendt;

    }

    /**
     * @return the appearance
     */
    public BEAppearance getAppearance() {
        return appearance;
    }

    /**
     * @param appearance the appearance to set
     */
    public void setAppearance(BEAppearance appearance) {
        this.appearance = appearance;
    }

    /**
     * @return the godkendtTid
     */
    public Timestamp getGodkendtTid() {
        return godkendtTid;
    }

    /**
     * @param godkendtTid the godkendtTid to set
     */
    public void setGodkendtTid(Timestamp godkendtTid) {
        this.godkendtTid = godkendtTid;
    }

    /**
     * @return the login
     */
    public BELogin getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(BELogin login) {
        this.login = login;
    }

    /**
     * @return the bemærkninger
     */
    public String getBemærkninger() {
        return bemærkninger;
    }

    /**
     * @param bemærkninger the bemærkninger to set
     */
    public void setBemærkninger(String bemærkninger) {
        this.bemærkninger = bemærkninger;
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
     * @return the gruppeNo
     */
    public String getGruppeNo() {
        return gruppeNo;
    }

    /**
     * @param gruppeNo the gruppeNo to set
     */
    public void setGruppeNo(String gruppeNo) {
        this.gruppeNo = gruppeNo;
    }

    /**
     * @return the ilGodkendt
     */
    public boolean isIlGodkendt() {
        return ilGodkendt;
    }

    /**
     * @param ilGodkendt the ilGodkendt to set
     */
    public void setIlGodkendt(boolean ilGodkendt) {
        this.ilGodkendt = ilGodkendt;
    }

    /**
     * @return the detektorNo
     */
    public String getDetektorNo() {
        return detektorNo;
    }

    /**
     * @param detektorNo the detektorNo to set
     */
    public void setDetektorNo(String detektorNo) {
        this.detektorNo = detektorNo;
    }

    /**
     * @return the alarm
     */
    public BEAlarm getAlarm() {
        return alarm;
    }

    /**
     * @param alarm the alarm to set
     */
    public void setAlarm(BEAlarm alarm) {
        this.alarm = alarm;
    }

}
