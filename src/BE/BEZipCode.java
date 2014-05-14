package BE;

/**
 *
 * @author Team Kawabunga
 */
public class BEZipCode {

    private int zipCode;
    private String city;

    public BEZipCode(int zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    /**
     * @return the ZIPCODE
     */
    public int getZIPCODE() {
        return zipCode;
    }

    /**
     * @param ZIPCODE the ZIPCODE to set
     */
    public void setZIPCODE(int ZIPCODE) {
        this.zipCode = ZIPCODE;
    }

    /**
     * @return the CITY
     */
    public String getCITY() {
        return city;
    }

    /**
     * @param CITY the CITY to set
     */
    public void setCITY(String CITY) {
        this.city = CITY;
    }
}
