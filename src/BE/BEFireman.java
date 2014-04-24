package BE;

public class BEFireman {

    BEMedarbejder medarbjeder;
    boolean holdleder;
    boolean chaffør;
    boolean isCheckedin;
      String name;
    public static int amount = 0;

    public BEFireman(BEMedarbejder medarbejderRef, boolean holdleder, boolean chauffør, boolean isCheckIn){
    
        medarbjeder = medarbejderRef;
        this.holdleder = holdleder;
        this.chaffør = chauffør;
        this.isCheckedin = isCheckIn;
        amount++;
    }
        
   
    public int getAmount() {
        return amount;
    }
    
    public boolean isCheckedIn(){
        return isCheckedin;
    }

    @Override
    public String toString() {
        if (isCheckedIn())
        {
            return medarbjeder.fornavn + medarbjeder.mellemnavn + medarbjeder.efternavn + " (CHECKED IN)";
        }
        return name;
    }
}
