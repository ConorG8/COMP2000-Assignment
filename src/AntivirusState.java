import java.awt.Color;

public class AntivirusState implements CellState {
    @Override public String getType() { return "ANTIVIRUS"; }
    @Override public Color getCellColor() { return new Color(46, 61, 195); }

    @Override
    public CellState reactWith(CellState opponent) {
        if (opponent.getType().equals("NEUTRAL")) {
            return this;  // stay the same;
        } else if (opponent.getType().equals("INFECTED")) {
            return new NeutralState(); // "Use" the antivirus.
        } else {
            return this;  // Anti on Anti. Nothing happens.
        }
    }
}