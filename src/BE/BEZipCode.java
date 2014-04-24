package BE;


public class BEZipCode {
    private int ZIPCODE;
    private String CITY;

    public BEZipCode(int zipCode, String city) {
       ZIPCODE = zipCode;
       CITY = city;
    }

    /**
     * @return the ZIPCODE
     */
    public int getZIPCODE() {
        return ZIPCODE;
    }

    /**
     * @param ZIPCODE the ZIPCODE to set
     */
    public void setZIPCODE(int ZIPCODE) {
        this.ZIPCODE = ZIPCODE;
    }

    /**
     * @return the CITY
     */
    public String getCITY() {
        return CITY;
    }

    /**
     * @param CITY the CITY to set
     */
    public void setCITY(String CITY) {
        this.CITY = CITY;
    }
}