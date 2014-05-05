package BE;

/**
 *
 * @author Team Kawabunga
 */
public class BEAlarmKøtj {
    
    private int id;
    private BEAlarm alarm;
    private BEVehicle veh;
    
    public BEAlarmKøtj(int id, BEAlarm alarm, BEVehicle veh){
        this.id = id;
        this.alarm = alarm;
        this.veh = veh;
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
}
