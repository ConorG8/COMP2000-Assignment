import java.awt.Color;
import java.awt.Graphics;

public class Cell implements Drawable {
    private int x, y;
    private final int size = 20;
    private String id;
    private CharacterState state;

    public Cell(int startX, int startY, String id, CharacterState initialState){
        this.x = startX;
        this.y = startY;
        this.id = id;
        this.state = initialState;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getSize() { return size; }
    public String getId() { return id; }
    public CharacterState getState() { return state; }

    public void changeState(CharacterState newState){
        this.state = newState;
    }

    public void draw(Graphics g){
        g.setColor(state.getCellColor());

        g.fillOval(x, y, size, size);

        g.setColor(Color.BLACK);
        g.drawOval(x, y, size, size);
    }

    public int onCollision(Cell opponent){
        return this.state.checkMatchup(opponent.getState());
    }
}
