package BE;

public class BEFireman {

    boolean isCheckedIn;
    String name;
    public static int amount = 0;

    public BEFireman(String name, boolean isCheckedIn) {
        this.name = name;
        this.isCheckedIn = isCheckedIn;
        amount++;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
    public boolean isCheckedIn(){
        return isCheckedIn;
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
