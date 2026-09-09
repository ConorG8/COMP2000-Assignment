public class BerserkState {
    boolean isBerserk;
    int enterBerserkChance;

    long berserkStartTime;
    int berserkDuration = 5000;

    public BerserkState() {
        this.isBerserk = false;
        this.enterBerserkChance = 5;
    }

    public void EnterBerserk() throws IllegalBerserkException{

        if (this.enterBerserkChance <= 0) {
            throw new IllegalBerserkException("No berserk uses remaining"); // prevent calling  enterBerserk when no Berserk chances left
        }
        if (this.isBerserk) {
            throw new IllegalBerserkException("No berserk uses remaining"); // prevent calling enterBerserk when infected cells are already in berserk state
        }

        this.isBerserk = true;
        this.enterBerserkChance -= 1;
        this.berserkStartTime = System.currentTimeMillis();
    }

    public void ExitBerserk() {
        this.isBerserk = false;
    }

    public boolean isBerserk() {
        if (isBerserk && System.currentTimeMillis() - berserkStartTime >= berserkDuration) {
            ExitBerserk(); 
        }
        return isBerserk;
    }
}

class IllegalBerserkException extends Exception {
    public IllegalBerserkException(String message) {
        super(message);
    }
}