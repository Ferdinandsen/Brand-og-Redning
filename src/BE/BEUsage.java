package BE;

/**
 *
 * @author Team Kawabunga
 */
public class BEUsage {

    private int id;
    private BEMateriel materiel;
    private int amount;
    private BEAlarm alarm;

    public BEUsage(int id,BEMateriel materiel, int amount, BEAlarm alarm) {
        this.id = id;
        this.materiel = materiel;
        this.amount = amount;
        this.alarm = alarm;
    }
    public BEUsage(BEMateriel materiel, int amount, BEAlarm alarm) {
        this.materiel = materiel;
        this.amount = amount;
        this.alarm = alarm;
    }
    
    public BEAlarm getAlarm() {
        return alarm;
    }

    /**
     * @param forbrug the forbrug to set
     */
    public void setAlarm(BEAlarm alarm) {
        this.alarm = alarm;
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
