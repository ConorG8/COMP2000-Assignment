public abstract class StateMachine implements CharacterState {
    @Override
    public int checkMatchup(CharacterState opponent) {
        if (this.getType().equals(opponent.getType())) return 0; // Tie
        
        switch (this.getType()) {
            case "INFECTED":  return opponent.getType().equals("NEUTRAL") ? 1 : -1;
            case "ANTIVIRUS": return opponent.getType().equals("INFECTED") ? 1 : -1;
            case "NEUTRAL":   return opponent.getType().equals("ANTIVIRUS") ? 1 : -1;
            default: return 0;
        }
    }
}
