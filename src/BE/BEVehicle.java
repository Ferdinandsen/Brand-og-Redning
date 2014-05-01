package BE;

/**
 *
 * @author Team Kawabunga
 */
public class BEVehicle {

    private int odinnummer;
    private String licensplate;
    private String mark;
    private String model;
    private String description;

    public BEVehicle(int odin, String reg, String mark, String mod, String desc) {
        odinnummer = odin;
        licensplate = reg;
        this.mark = mark;
        model = mod;
        description = desc;
    }

    /**
     * @return the odinnummer
     */
    public int getOdinnummer() {
        return odinnummer;
    }

    /**
     * @param odinnummer the odinnummer to set
     */
    public void setOdinnummer(int odinnummer) {
        this.odinnummer = odinnummer;
    }

    /**
     * @return the registrering
     */
    public String getRegistrering() {
        return licensplate;
    }

    /**
     * @param registrering the registrering to set
     */
    public void setRegistrering(String registrering) {
        this.licensplate = registrering;
    }

    /**
     * @return the mærke
     */
    public String getMærke() {
        return mark;
    }

    /**
     * @param mærke the mærke to set
     */
    public void setMærke(String mærke) {
        this.mark = mærke;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the beskrivelse
     */
    public String getBeskrivelse() {
        return description;
    }

    /**
     * @param beskrivelse the beskrivelse to set
     */
    public void setBeskrivelse(String beskrivelse) {
        this.description = beskrivelse;
    }

    @Override
    public String toString() {
        return "" + odinnummer;
    }
}
