import java.awt.Color;

public class InfectedState implements CellState {
    public static final InfectedState INSTANCE = new InfectedState(); // So we don't create a new state object on each reaction

    private InfectedState() { }

    @Override public String getType() { return "INFECTED"; }
    @Override public Color getCellColor() { return new Color(255, 0, 0); }

    @Override
    public CellState reactWith(CellState opponent) {
        if (opponent.getType().equals("NEUTRAL")) {
            return this;
        } else if (opponent.getType().equals("ANTIVIRUS")) {
            return NeutralState.INSTANCE; 
        } else {
            return this;
        }
    }
}