package BE;

public class BEFiremanTest {
    boolean holdleder;
    boolean chaffør;
    boolean isCheckedin;
      String name;
    public static int amount = 0;

    public BEFiremanTest(String name, boolean holdleder, boolean chauffør, boolean isCheckIn){
this.name = name;
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
            return name + " (CHECKED IN)";
        }
        return name;
    }
}
