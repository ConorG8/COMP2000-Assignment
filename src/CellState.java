import java.awt.Color;

public interface CellState {
    String getType();
    Color getCellColor();
    CellState reactWith(CellState opponent); 
}
