public abstract class StateMachine implements CellState {
    @Override
    public int checkMatchup(CellState opponent) {
        if (this.getType().equals(opponent.getType())) return 0; // Tie
        
        switch (this.getType()) {
            case "INFECTED":  
                if (opponent.getType().equals("NEUTRAL")) {
                    return 1;
                } else {
                    return -1;
                }
            case "ANTIVIRUS": 
                if (opponent.getType().equals("INFECTED")) {
                    return 1;
                } else {
                    return -1;
                }
            case "NEUTRAL":   
                if(opponent.getType().equals("ANTIVIRUS")) {
                    return 1;
                } else {
                    return -1;
                }
            default: return 0;
        }
    }
}
