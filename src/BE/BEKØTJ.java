package BE;

/**
 *
 * @author Jacob
 */
public class BEKØTJ {
    private int odinnummer;
    private String registrering;
    private String mærke;
    private String model;
    private String beskrivelse;

public BEKØTJ (int odin, String reg, String mark, String mod, String desc){
    odinnummer = odin;
    registrering = reg;
    mærke = mark;
    model = mod;
    beskrivelse = desc;    
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
        return registrering;
    }

    /**
     * @param registrering the registrering to set
     */
    public void setRegistrering(String registrering) {
        this.registrering = registrering;
    }

    /**
     * @return the mærke
     */
    public String getMærke() {
        return mærke;
    }

    /**
     * @param mærke the mærke to set
     */
    public void setMærke(String mærke) {
        this.mærke = mærke;
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
        return beskrivelse;
    }

    /**
     * @param beskrivelse the beskrivelse to set
     */
    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

}
