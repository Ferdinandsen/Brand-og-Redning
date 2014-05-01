package BE;

public class BEAppearance {
    private int id;
    private BEVehicle vehicle;
    private BETime time;
    private int totalTid;
    private int evaNo;
    private boolean isGodkendt;
    private boolean holdleder;
    private boolean chauffør;
    private boolean STvagt;
    
    public BEAppearance(int id, BEVehicle vehicle, BETime time, int totaltid, int evaNo, boolean isGodkendt, boolean holdleder, boolean chauffør, boolean STvagt){
       
        this.id = id;
        this.vehicle = vehicle;
        this.time = time;
        this.totalTid = totaltid;
        this.evaNo = evaNo;
        this.isGodkendt = isGodkendt;
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
        return isGodkendt;
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
   
}
