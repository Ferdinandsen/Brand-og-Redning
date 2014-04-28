package BE;

public class BEFireman {

     private BEEmployee medarbjeder;
    private boolean holdleder;
    private boolean chaffør;
    private boolean isCheckedIn;
    private String name;
    private static int amount = 0;

    public BEFireman(BEEmployee medarbejderRef, boolean holdleder, boolean chauffør, boolean isCheckIn) {

        medarbjeder = medarbejderRef;
        this.holdleder = holdleder;
        this.chaffør = chauffør;
        this.isCheckedIn = isCheckIn;
        amount++;
    }

       /**
     * @param aAmount the amount to set
     */
    public static void setAmount(int aAmount) {
        amount = aAmount;
    }
    
    
    public static int getAmount() {
        return amount;
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
    public boolean isCheckedin() {
        return isCheckedIn;
    }

    /**
     * @param isCheckedin the isCheckedin to set
     */
    public void setIsCheckedin(boolean isCheckedin) {
        this.isCheckedIn = isCheckedin;
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

    @Override
    public String toString() {
        if (isCheckedIn) {
            return getMedarbjeder().getFornavn() + getMedarbjeder().getMellemnavn() + getMedarbjeder().getEfternavn() + " (CHECKED IN)";
        }
        return getName();
    }
}
