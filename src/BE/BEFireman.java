package BE;

public class BEFireman {

    String name;
    public static int amount = 0;

    public BEFireman(String name) {
        this.name = name;
        amount++;
    }

    public String getName() {
        return name;
    }
    public int getAmount(){
        return amount;
    }
    @Override
    public String toString(){
        return name;
    }
}
