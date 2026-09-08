/**
 * Cell immunity
 * after being cured, the cell gains a chance to gain immunity from an infected cell
 */
public class CellImmunity {
    private boolean immune = false;
    private int immuneTime = 3;
    private float chanceOfImmunity = 0.7f;

    /**
     * Check if the cell survives colliding with infected cell
     * @return
     */
    public boolean calculateIfImmune() {

        return Math.random() < chanceOfImmunity;
    }

    

}
