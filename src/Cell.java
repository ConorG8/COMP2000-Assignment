import java.awt.Color;
import java.awt.Graphics;

public class Cell {
    private double x;
    private double y;
    private final int size = 20;
    private int id;
    private CellState state;

    public Cell(double startX, double startY, int id, CellState initialState) {
        this.x = startX;
        this.y = startY;
        this.id = id;
        this.state = initialState;
    }       

    public double getX() { return x; }
    public double getY() { return y; }
    public int getSize() { return size; }
    public int getId() { return id; }
    public CellState getState() { return state; }

    public void changeState(CellState newState){
        this.state = newState;
    }

    public void draw(Graphics g){
        g.setColor(state.getCellColor());

        g.fillOval((int) x, (int) y, size, size);

        g.setColor(Color.BLACK);
        g.drawOval((int) x, (int) y, size, size);
    }

    public void onCollision(Cell opponent){
        CellState oppNewState = opponent.state.reactWith(this.getState());
        CellState thisNewState = this.state.reactWith(opponent.getState());
        changeState(thisNewState);
        opponent.changeState(oppNewState);
    }

    public Cell checkCollisions() {
        // get x and y of self and check if it collided with another cell
        
        return null;
    }
}
