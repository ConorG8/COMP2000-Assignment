import java.awt.Color;

public class InfectedState implements CellState {
    @Override public String getType() { return "INFECTED"; }
    @Override public Color getCellColor() { return new Color(255, 0, 0); }

    @Override
    public CellState reactWith(CellState opponent) {
        if (opponent.getType().equals("NEUTRAL")) {
            return this;  // Infected meeting Neutral. Don't Change
        } else if (opponent.getType().equals("ANTIVIRUS")) {
            return new NeutralState();  // Infected meeting Antivirus. Become Neutral
        } else {
            return this;  // Infected meeting Infected. No change
        }
    }
}