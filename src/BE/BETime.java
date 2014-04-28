package BE;

import java.sql.Timestamp;

/**
 *
 * @author André
 */
public class BETime {

    private int medarbejderNo;
    private Timestamp checkIn;
    private Timestamp checkOut;
    
    
    public BETime(int medarbejderNo, Timestamp checkIn, Timestamp checkOut){
        
        this.medarbejderNo = medarbejderNo;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        
    }

    /**
     * @return the medarbejderNo
     */
    public int getMedarbejderNo() {
        return medarbejderNo;
    }

    /**
     * @param medarbejderNo the medarbejderNo to set
     */
    public void setMedarbejderNo(int medarbejderNo) {
        this.medarbejderNo = medarbejderNo;
    }

    /**
     * @return the fornavn
     */
    public Timestamp getCheckInTime() {
        return checkIn;
    }

    public Timestamp getCheckOutTime() {
        return checkOut;
    }

}
