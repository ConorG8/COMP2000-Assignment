public class BerserkState {
    boolean isBerserk;
    int enterBerserkChance;

    public BerserkState() {
        this.isBerserk = false;
        this.enterBerserkChance = 1;
    }

    public void EnterBerserk() {
        this.isBerserk = true;
        //this.enterBerserkChance -= 1;
    }

    public boolean getIsBerserk() {
        return this.isBerserk;
    }
}
