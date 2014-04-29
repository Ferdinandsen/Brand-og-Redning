package BE;

import java.sql.Timestamp;

/**
 *
 * @author André
 */
public class BETime {

    private BEFireman fireman;
    private Timestamp checkIn;
    private Timestamp checkOut;
    private boolean hasCheckedOut;
    
    
    public BETime(BEFireman fireman, Timestamp checkIn, Timestamp checkOut,boolean hasCheckedOut){
        
        this.fireman = fireman;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.hasCheckedOut = hasCheckedOut;
        
    }

    /**
     * @return the medarbejderNo
     */
    public BEFireman getFireman() {
        return fireman;
    }

    /**
     * @param medarbejderNo the medarbejderNo to set
     */
    public void setFireman(BEFireman fireman) {
        this.fireman = fireman;
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
     * @return the checkOut
     */
    public Timestamp getCheckOut() {
        return checkOut;
    }

    /**
     * @param checkOut the checkOut to set
     */
    public void setCheckOut(Timestamp checkOut) {
        this.checkOut = checkOut;
    }

    /**
     * @return the hasCheckedOut
     */
    public boolean isHasCheckedOut() {
        return hasCheckedOut;
    }

    /**
     * @param hasCheckedOut the hasCheckedOut to set
     */
    public void setHasCheckedOut(boolean hasCheckedOut) {
        this.hasCheckedOut = hasCheckedOut;
    }

}
