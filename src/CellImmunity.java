import java.util.Timer;
import java.util.TimerTask;

/**
 * Cell immunity
 * after being cured, the cell gains a chance to gain immunity from an infected cell
 */
public class CellImmunity {
    private boolean immune = false;
    private int curedCount = 0;
    private long immuneTime = 3000;
    private float chanceOfImmunity = 0.7f;
    private Timer timer = new Timer();

    

    public void activateImmunity() {
        this.immune = calculateIfImmune();
        
    }

    /**
     * Check if the cell survives colliding with infected cell
     * @return
     */
    public boolean calculateIfImmune() {
        if (curedCount > 1) {
            return Math.random() < chanceOfImmunity;
        } else {
            return false;
        }
    }

    

}
