package BE;

import java.sql.Timestamp;

/**
 *
 * @author Team Kawabunga
 */
public class BEAppearance extends IBESubject{

    private int id;
    private BEFireman fireman;
    private int totalTid;
    private Timestamp checkIn;
    private Timestamp checkOut;
    private boolean hlGodkendt;
    private boolean holdleder;
    private boolean chauffør;
    private boolean STvagt;
    private boolean ilGodkendt;
    private int kørselsType;
    private BEAlarm alarm;
    private BEVehicle veh;
    private BELogin login;
    private boolean lønDone;
    private Timestamp lønTime;
    

    public BEAppearance(int id, BEFireman fireman, Timestamp checkIn,
            Timestamp checkOut, int totaltid, boolean hlGodkendt, 
            boolean ilGodkendt, boolean holdleder, boolean chauffør, 
            boolean STvagt, int type, BEAlarm alarm, BEVehicle veh, 
            BELogin login, boolean lønDone, Timestamp lønTime) {
        this.id = id;
        this.alarm = alarm;
        this.fireman = fireman;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.kørselsType = type;
        this.totalTid = totaltid;
        this.hlGodkendt = hlGodkendt;
        this.ilGodkendt = ilGodkendt;
        this.holdleder = holdleder;
        this.chauffør = chauffør;
        this.STvagt = STvagt;
        this.veh = veh;
        this.login = login;
        this.lønDone = lønDone;
        this.lønTime = lønTime;
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
     * @param fireman
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
     * @return the kørselsType
     */
    public int getType() {
        return getKørselsType();
    }

    /**
     * @param type the kørselsType to set
     */
    public void setType(int type) {
        this.setKørselsType(type);
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
    
/**
 * 
 * @return 
 */
    public String getCheckOutString() {
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

    /**
     * @return the kørselsType
     */
    public int getKørselsType() {
        return kørselsType;
    }

    /**
     * @param kørselsType the kørselsType to set
     */
    public void setKørselsType(int kørselsType) {
        this.kørselsType = kørselsType;
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
     * @return the lønDone
     */
    public boolean isLønDone() {
        return lønDone;
    }

    /**
     * @param lønDone the lønDone to set
     */
    public void setLønDone(boolean lønDone) {
        this.lønDone = lønDone;
    }

    /**
     * @return the lønTime
     */
    public Timestamp getLønTime() {
        return lønTime;
    }

    /**
     * @param lønTime the lønTime to set
     */
    public void setLønTime(Timestamp lønTime) {
        this.lønTime = lønTime;
    }

    
}
