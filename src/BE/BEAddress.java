package BE;

public class BEAddress {
    private int ID;
    private String STREETNAME;
    private int STREETNUMBER;
    private int FLOOR;
    private String APARTMENT;
    private BEZipCode ZIP;

    public BEAddress(int id, String streetname, int streetNumber, int floor, String apartment, BEZipCode zip) {
       ID = id;
       STREETNAME = streetname;
       STREETNUMBER = streetNumber;
       FLOOR = floor;
       APARTMENT = apartment;
       ZIP = zip;
    }
    public BEAddress(String streetname, int streetNumber, int floor, String apartment, BEZipCode zip) {
       STREETNAME = streetname;
       STREETNUMBER = streetNumber;
       FLOOR = floor;
       APARTMENT = apartment;
       ZIP = zip;
    }

    /**
     * @return the id
     */
    public int getId() {
        return ID;
    }
    public int getStreetNumber(){
        return STREETNUMBER;
    }

    /**
     * @return the streetName
     */
    public String getStreetName() {
        return STREETNAME;
    }

    /**
     * @return the floor
     */
    public int getFloor() {
        return FLOOR;
    }

    /**
     * @return the apartment
     */
    public String getApartment() {
        return APARTMENT;
    }
    public BEZipCode getZipCode(){
        return getZip();
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.ID = id;
    }

    /**
     * @param streetName the streetName to set
     */
    public void setStreetName(String streetName) {
        this.STREETNAME = streetName;
    }

    /**
     * @param streetNumber the streetNumber to set
     */
    public void setStreetNumber(int streetNumber) {
        this.STREETNUMBER = streetNumber;
    }

    /**
     * @param floor the floor to set
     */
    public void setFloor(int floor) {
        this.FLOOR = floor;
    }

    /**
     * @param apartment the apartment to set
     */
    public void setApartment(String apartment) {
        this.APARTMENT = apartment;
    }

    /**
     * @return the zip
     */
    public BEZipCode getZip() {
        return ZIP;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(BEZipCode zip) {
        this.ZIP = zip;
    }
}