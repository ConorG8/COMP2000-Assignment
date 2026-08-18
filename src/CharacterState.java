import java.awt.Color;

public interface CharacterState {
    String getType();
    Color getCellColor();
    int checkMatchup(CharacterState opponent);
}
