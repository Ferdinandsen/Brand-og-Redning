package BE;

/**
 *
 * @author Team Kawabunga
 */
public class BEAddress {
    private int ID;
    private String streetName;
    private int streetNumber;
    private int floor;
    private String apartmnet;
    private BEZipCode zip;

    public BEAddress(int id, String streetname, int streetNumber, int floor, String apartment, BEZipCode zip) {
       this.ID = id;
       this.streetName = streetname;
       this.streetNumber = streetNumber;
       this.floor = floor;
       this.apartmnet = apartment;
       this.zip = zip;
    }
    public BEAddress(String streetname, int streetNumber, int floor, String apartment, BEZipCode zip) {
       this.streetName = streetname;
       this.streetNumber = streetNumber;
       this.floor = floor;
       this.apartmnet = apartment;
       this.zip = zip;
    }

    /**
     * @return the id
     */
    public int getId() {
        return ID;
    }
    public int getStreetNumber(){
        return streetNumber;
    }

    /**
     * @return the streetName
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * @return the floor
     */
    public int getFloor() {
        return floor;
    }

    /**
     * @return the apartment
     */
    public String getApartment() {
        return apartmnet;
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
        this.streetName = streetName;
    }

    /**
     * @param streetNumber the streetNumber to set
     */
    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * @param floor the floor to set
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * @param apartment the apartment to set
     */
    public void setApartment(String apartment) {
        this.apartmnet = apartment;
    }

    /**
     * @return the zip
     */
    public BEZipCode getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(BEZipCode zip) {
        this.zip = zip;
    }
}