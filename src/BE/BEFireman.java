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
    private int team;

    public BEFireman(BEEmployee medarbejderRef, boolean holdleder, boolean chauffør, int team) {

        medarbjeder = medarbejderRef;
        this.holdleder = holdleder;
        this.chaffør = chauffør;
        this.team = team;
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

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return getMedarbjeder().getFornavn() + " " + getMedarbjeder().getEfternavn();
    }

    /**
     * @return the team
     */
    public int getTeam() {
        return team;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(int team) {
        this.team = team;
    }
}
