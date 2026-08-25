import java.awt.Color;
import java.awt.Graphics;

// Cell Class
public class Cell {
    private double x;
    private double y;
    private double velX;
    private double velY;
    private final int size = 20;
    private int id;
    private CellState state;

    public Cell(double startX, double startY, int id, CellState initialState) {
        this.x = startX;
        this.y = startY;
        this.id = id;
        this.state = initialState;
        this.velX = (Math.random() * 6) - 3;
        this.velY = (Math.random() * 6) - 3;
    }       

    public double getX() { return x; }
    public double getY() { return y; }
    public int getSize() { return size; }
    public int getId() { return id; }
    public CellState getState() { return state; }

    public void changeState(CellState newState){
        this.state = newState;
    }

    public void draw(Graphics g) {
        g.setColor(state.getCellColor());

        g.fillOval((int) x, (int) y, size, size);

        g.setColor(this.getState().getCellColor());
        g.drawOval((int) x, (int) y, size, size);
    }

    public void onCollision(Cell opponent){
        CellState oppNewState = opponent.state.reactWith(this.getState());
        CellState thisNewState = this.state.reactWith(opponent.getState());
        changeState(thisNewState);
        opponent.changeState(oppNewState);
    }

    public void move(int panelWidth, int panelHeight) {
    if (panelWidth <= 0 || panelHeight <= 0) {
        return; 
    }
    x += velX;
    y += velY;

    if (x <= 0) {
        x = 0;
        velX = -velX;
    } else if (x + size >= panelWidth) {
        x = panelWidth - size;
        velX = -velX;
    }

    if (y <= 0) {
        y = 0;
        velY = -velY;
    } else if (y + size >= panelHeight) {
        y = panelHeight - size;
        velY = -velY;
    }
}


    /* public Cell checkCollisions() {
        // get x and y of self and check if it collided with another cell
        
        return null;
    } */
}
