import java.awt.Color;

public class NeutralState implements CellState {
    @Override public String getType() { return "NEUTRAL"; }
    @Override public Color getCellColor() { return new Color(0, 255, 0); }

    @Override
    public CellState reactWith(CellState opponent) {
        if (opponent.getType().equals("INFECTED")) {
            return new InfectedState(); // Neutral becomes Infected
        } else if (opponent.getType().equals("ANTIVIRUS")) {
            return new AntivirusState();  // Antivirus change
        } else {
            return this;  // Neutral on Neutral. No change
        }
    }
}