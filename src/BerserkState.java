public class BerserkState {
    boolean isBerserk;
    int enterBerserkChance;

    long berserkStartTime;
    int berserkDuration = 5000;

    public BerserkState() {
        this.isBerserk = false;
        this.enterBerserkChance = 5;
    }

    public void EnterBerserk() {
        this.isBerserk = true;
        this.enterBerserkChance -= 1;
        this.berserkStartTime = System.currentTimeMillis();
    }

    public void ExitBerserk() {
        this.isBerserk = false;
    }

    public boolean isBerserk() {
        if (isBerserk && System.currentTimeMillis() - berserkStartTime >= berserkDuration) {
            isBerserk = false;
            System.out.println("A");
            
        }
        return isBerserk;
    }
}
