package BE;

/**
 *
 * @author Team Kawabunga
 */
public class BEMateriel {
    
    private int id;
    private String name;
    private static int amount;
    
    
    public BEMateriel(int id, String name){
        
        this.id = id;
        this.name = name;
        amount++;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
