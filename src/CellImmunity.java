/**
 * Cell immunity
 * after being cured, the cell gains a chance to gain immunity from an infected cell
 */
public class CellImmunity {
    private boolean immune = false;
    private int immunityLevel;
    private int immuneTime;


    public int getImmunityLevel() {
        return immunityLevel;
    }


    /**
     * Check if the cell survives colliding with infected cell
     * @return
     */
    public boolean calculateIfImmune() {

        return Math.random() < 0.7;
    }

    

}
