package BE;

public class BEFireman {

    BEMedarbejder medarbjeder;
    boolean holdleder;
    boolean chaffør;
    boolean isCheckin;
    public static int AMOUNT = 0;

    public BEFireman(BEMedarbejder medarbejderRef, boolean holdleder, boolean chauffør, boolean isCheckIn){
    
        medarbjeder = medarbejderRef;
        this.holdleder = holdleder;
        this.chaffør = chauffør;
        this.isCheckin = isCheckIn;
        AMOUNT++;
    }
       public int getAmount(){
        return AMOUNT;
    }
    @Override
    public String toString(){
        return medarbjeder.fornavn + medarbjeder.mellemnavn + medarbjeder.efternavn;
    }
}
