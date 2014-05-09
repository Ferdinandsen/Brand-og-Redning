package BE;

/**
 *
 * @author Team Kawabunga
 */
public class BEFireman {
    private BEEmployee medarbjeder;
    private boolean holdleder;
    private boolean chaffør;
    private String name;

    public BEFireman(BEEmployee medarbejderRef, boolean holdleder, boolean chauffør) {

        medarbjeder = medarbejderRef;
        this.holdleder = holdleder;
        this.chaffør = chauffør;
    }

    /**
     * @return the medarbjeder
     */
    public BEEmployee getMedarbjeder() {
        return medarbjeder;
    }

    /**
     * @param medarbjeder the medarbjeder to set
     */
    public void setMedarbjeder(BEEmployee medarbjeder) {
        this.medarbjeder = medarbjeder;
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
     * @return the chaffør
     */
    public boolean isChaffør() {
        return chaffør;
    }

    /**
     * @param chaffør the chaffør to set
     */
    public void setChaffør(boolean chaffør) {
        this.chaffør = chaffør;
    }

    /**
     * @return the isCheckedin
     */

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        if (getMedarbjeder().getMellemnavn().equals("")) {
                return getMedarbjeder().getFornavn() + " " + getMedarbjeder().getEfternavn();
        }
        else
        {
                return getMedarbjeder().getFornavn() + " " + getMedarbjeder().getMellemnavn() + " " + getMedarbjeder().getEfternavn() + " (CHECKED IN)";
        }
    }
}
