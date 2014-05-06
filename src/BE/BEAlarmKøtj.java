package BE;

/**
 *
 * @author Team Kawabunga
 */
public class BEAlarmKøtj {

    private int id;
    private BEAlarm alarm;
    private BEVehicle veh;
    private boolean isConfirmed;

    public BEAlarmKøtj(int id, BEAlarm alarm, BEVehicle veh, boolean confirm) {
        this.id = id;
        this.alarm = alarm;
        this.veh = veh;
        isConfirmed = confirm;
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

    /**
     * @return the veh
     */
    public BEVehicle getVeh() {
        return veh;
    }

    /**
     * @param veh the veh to set
     */
    public void setVeh(BEVehicle veh) {
        this.veh = veh;
    }

    @Override
    public String toString() {
        String[] test = getAlarm().getTime().toString().split(" ");
        return "" + getVeh().getOdinnummer() + " - " + test[1].substring(0, 5);
    }

    /**
     * @return the isConfirmed
     */
    public boolean isIsConfirmed() {
        return isConfirmed;
    }

    /**
     * @param isConfirmed the isConfirmed to set
     */
    public void setIsConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

}
