package BE;

import java.sql.Timestamp;

/**
 *
 * @author Team Kawabunga
 */
public class BETime {
    private int id;
    private BEFireman fireman;
    private Timestamp checkOut;
    private boolean hasCheckedOut;
    private boolean complete;
    
   public BETime(BEFireman fireman, Timestamp checkOut,boolean hasCheckedOut,boolean complete){
        this.fireman = fireman;
        this.checkOut = checkOut;
        this.hasCheckedOut = hasCheckedOut;
        this.complete = complete;  
    }
   
    public BETime(int id,BEFireman fireman, Timestamp checkIn, Timestamp checkOut,boolean hasCheckedOut,boolean complete){
        this.id = id;
        this.fireman = fireman;
        this.checkOut = checkOut;
        this.hasCheckedOut = hasCheckedOut;
        this.complete = complete;  
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


    /**
     * @param checkIn the checkIn to set
     */

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
     * @return the complete
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * @param complete the complete to set
     */
    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
