import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Cell immunity
 * after being cured, the cell gains a chance to gain immunity from an infected cell
 */
public class CellImmunity {
    private boolean isImmune = false;
    private int curedCount = 0;
    private int maxImmunityCount = 3; // 
    private int hitCounts = 0;


    //private long immuneTime = 3000;
    private float chanceOfImmunity = 0.75f;
    private boolean canHaveImmunity = false;
    
    /*

    make timer

    set code to run every 3 seconds?

    check if immune if immune then give immunity for 2-3 collisions
    
    */

    public boolean getImmunityAllowed() {
         return canHaveImmunity;
    }

    public void setIfImmunityAllowed(boolean value) {
        this.canHaveImmunity = value;
    }

    public int getCuredCount() {
        return this.curedCount;
    }

    public void incrementCuredCount() {
        this.curedCount++;
    }
    
    public boolean getImmunity() {
        return this.isImmune;
    }

    public void setImmunity(boolean immunity) {
        this.isImmune = immunity;
    }

    public void checkIfImmune() {
        if (this.hitCounts < this.maxImmunityCount) {
            this.isImmune = true;
            this.hitCounts++;
        } else {
            this.isImmune = false;
            this.curedCount = 0;
            this.hitCounts = 0;
        }
    }

    /**
     * Check if the cell survives colliding with infected cell
     * @return
     */
    public boolean calculateIfImmune() {
        if (curedCount > 0) {
            return Math.random() < chanceOfImmunity;
        } else {
            return false;
        }
    }

    

}
