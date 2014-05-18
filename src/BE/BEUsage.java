package BE;

/**
 *
 * @author Team Kawabunga
 */
public class BEUsage {

    private int id;
    private BEAlarm alarm;
    private BEMateriel materiel;
    private int amount;

    public BEUsage(int id, BEAlarm alarm, BEMateriel materiel, int amount) {
        this.id = id;
        this.alarm = alarm;
        this.materiel = materiel;
        this.amount = amount;
    }
    public BEUsage(BEAlarm alarm, BEMateriel materiel, int amount) {
        this.alarm = alarm;
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

    /**
     * @return the alarm
     */
    public BEAlarm getAlarm() {
        return alarm;
    }

    /**
     * @param alarm the alarm to set
     */
    public void setAlarm(BEAlarm alarm) {
        this.alarm = alarm;
    }
}
