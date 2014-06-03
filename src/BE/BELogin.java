package BE;

/**
 *
 * @author Team Kawabunga
 */
public class BELogin {

    private BEEmployee medarbejder;
    private String kode;
    private boolean admin;
    private boolean kontor;
    private boolean holdleder;

    public BELogin(BEEmployee medarbejder, String kode, 
            boolean admin, boolean kontor, boolean holdleder) {

        this.medarbejder = medarbejder;
        this.kode = kode;
        this.admin = admin;
        this.kontor = kontor;
        this.holdleder = holdleder;
    }

    /**
     * @return the medarbejderRef
     */
    public BEEmployee getMedarbejder() {
        return medarbejder;
    }

    /**
     * @param medarbejder
     */
    public void setMedarbejder(BEEmployee medarbejder) {
        this.medarbejder = medarbejder;
    }

    /**
     * @return the kode
     */
    public String getKode() {
        return kode;
    }

    /**
     * @param kode the kode to set
     */
    public void setKode(String kode) {
        this.kode = kode;
    }

    /**
     * @return the admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * @return the kontor
     */
    public boolean isKontor() {
        return kontor;
    }

    /**
     * @param kontor the kontor to set
     */
    public void setKontor(boolean kontor) {
        this.kontor = kontor;
    }

    /**
     * @return the holdleder
     */
    public boolean isHoldleder() {
        return holdleder;
    }

    /**
     * @param holdleder the holdleder to set
     */
    public void setHoldleder(boolean holdleder) {
        this.holdleder = holdleder;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return getMedarbejder().getFornavn() + 
                " " + getMedarbejder().getEfternavn();
    }
}
