package BE;

public class BEAppearance {
    private int id;
    private BEVehicle vehicle;
    private BETime time;
    private int totalTid;
    private int evaNo;
    private boolean hlGodkendt;
    private boolean ilGodkendt;
    private boolean holdleder;
    private boolean chauffør;
    private boolean STvagt;
    
    public BEAppearance(int id, BEVehicle vehicle, BETime time, int totaltid, int evaNo, boolean hlGodkendt, boolean ilGodkendt, boolean holdleder, boolean chauffør, boolean STvagt){
        this.id = id;
        this.vehicle = vehicle;
        this.time = time;
        this.totalTid = totaltid;
        this.evaNo = evaNo;
        this.hlGodkendt = hlGodkendt;
        this.ilGodkendt = ilGodkendt;
        this.holdleder = holdleder;
        this.chauffør = chauffør;
        this.STvagt = STvagt;
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
    public BEVehicle getVehicle() {
        return vehicle;
    }

    /**
     * @return the time
     */
    public BETime getTime() {
        return time;
    }

    /**
     * @return the totalTid
     */
    public int getTotalTid() {
        return totalTid;
    }

    /**
     * @return the evaNo
     */
    public int getEvaNo() {
        return evaNo;
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
    public void setVehicle(BEVehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * @param time the time to set
     */
    public void setTime(BETime time) {
        this.time = time;
    }

    /**
     * @param totalTid the totalTid to set
     */
    public void setTotalTid(int totalTid) {
        this.totalTid = totalTid;
    }

    /**
     * @param evaNo the evaNo to set
     */
    public void setEvaNo(int evaNo) {
        this.evaNo = evaNo;
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

    
}
