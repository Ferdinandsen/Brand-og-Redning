package BE;

import java.sql.Timestamp;

/**
 *
 * @author Team Kawabunga
 */
public class BEAppearance {

    private int id;
    private BEAlarm alarm;
    private BEFireman fireman;
     private Timestamp checkIn;
    private Timestamp checkOut;
    private int totalTid;
    private boolean hlGodkendt;
    private boolean ilGodkendt;
    private boolean holdleder;
    private boolean chauffør;
    private boolean STvagt;
    private int type;
    private BEVehicle veh;
    
    public BEAppearance(int id, BEFireman fireman,Timestamp checkIn, Timestamp checkOut, int totaltid, boolean hlGodkendt, boolean ilGodkendt, 
            boolean holdleder, boolean chauffør, boolean STvagt, int type, BEAlarm alarm, BEVehicle veh){
        this.id = id;
        this.alarm = alarm;
        this.fireman = fireman;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.type = type;
        this.totalTid = totaltid;
        this.hlGodkendt = hlGodkendt;
        this.ilGodkendt = ilGodkendt;
        this.holdleder = holdleder;
        this.chauffør = chauffør;
        this.STvagt = STvagt;
        this.veh = veh;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the vehicle
     */
    public BEAlarm getAlarm() {
        return alarm;
    }

    /**
     * @return the time
     */
    public BEFireman getFireman() {
        return fireman;
    }

    /**
     * @return the totalTid
     */
    public int getTotalTid() {
        return totalTid;
    }

    /**
     * @return the isGodkendt
     */
    public boolean isIsGodkendt() {
        return isHlGodkendt();
    }

    /**
     * @return the holdleder
     */
    public boolean isHoldleder() {
        return holdleder;
    }

    /**
     * @return the chauffør
     */
    public boolean isChauffør() {
        return chauffør;
    }

    /**
     * @return the STvagt
     */
    public boolean isSTvagt() {
        return STvagt;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param vehicle the vehicle to set
     */
    public void setAlarm(BEAlarm vehicle) {
        this.alarm = vehicle;
    }

    /**
     * @param time the time to set
     */
    public void setFireman(BEFireman fireman) {
        this.fireman = fireman;
    }

    /**
     * @param totalTid the totalTid to set
     */
    public void setTotalTid(int totalTid) {
        this.totalTid = totalTid;
    }

    /**
     * @return the hlGodkendt
     */
    public boolean isHlGodkendt() {
        return hlGodkendt;
    }

    /**
     * @param hlGodkendt the hlGodkendt to set
     */
    public void setHlGodkendt(boolean hlGodkendt) {
        this.hlGodkendt = hlGodkendt;
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
     * @param holdleder the holdleder to set
     */
    public void setHoldleder(boolean holdleder) {
        this.holdleder = holdleder;
    }

    /**
     * @param chauffør the chauffør to set
     */
    public void setChauffør(boolean chauffør) {
        this.chauffør = chauffør;
    }

    /**
     * @param STvagt the STvagt to set
     */
    public void setSTvagt(boolean STvagt) {
        this.STvagt = STvagt;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
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

    /**
     * @return the checkOut
     */
    public Timestamp getCheckOut() {
        return checkOut;
    }
    public String getCheckOutString(){
        String checkOutString[] = getCheckOut().toString().split(" ");
        String[] fixedAlarm1 = checkOutString[1].split(":");
        String finalAlarm = fixedAlarm1[0] + ":" + fixedAlarm1[1];
        return finalAlarm;
    }

    /**
     * @param checkOut the checkOut to set
     */
    public void setCheckOut(Timestamp checkOut) {
        this.checkOut = checkOut;
    }

    /**
     * @return the checkIn
     */
    public Timestamp getCheckIn() {
        return checkIn;
    }

    /**
     * @param checkIn the checkIn to set
     */
    public void setCheckIn(Timestamp checkIn) {
        this.checkIn = checkIn;
    }
}
