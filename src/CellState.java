import java.awt.Color;

public interface CellState {
    String getType();
    Color getCellColor();
    int checkMatchup(CellState opponent);
}
