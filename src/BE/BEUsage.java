package BE;

/**
 *
 * @author Jacob
 */
public class BEUsage {

    private int id;
    private int evano;
    private BEMateriel materiel;
    private int amount;

    public BEUsage(int evano, BEMateriel materiel, int amount) {
        
        this.evano = evano;
        this.materiel = materiel;
        this.amount = amount;
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
     * @return the evano
     */
    public int getEvano() {
        return evano;
    }

    /**
     * @param evano the evano to set
     */
    public void setEvano(int evano) {
        this.evano = evano;
    }

    /**
     * @return the materiel
     */
    public BEMateriel getMateriel() {
        return materiel;
    }

    /**
     * @param materiel the materiel to set
     */
    public void setMateriel(BEMateriel materiel) {
        this.materiel = materiel;
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
