import java.awt.Color;
import java.awt.Graphics;

// Cell Class
public class Cell {
    private double x;
    private double y;
    private double velX;
    private double velY;

    private int size = 20;
    private int id;
    private CellState state;

    private int tick = 0;

    public Cell(double startX, double startY, int id, CellState initialState) {
        this.x = startX;
        this.y = startY;
        this.id = id;
        this.state = initialState;
        this.velX = (Math.random() * 3)-1;
        this.velY = (Math.random() * 3)-1;
    }       

    public double getX() { return x; }
    public double getY() { return y; }
    public int getSize() { return size; }
    public int getId() { return id; }
    public CellState getState() { return state; }

    public void changeState(CellState newState){
        this.state = newState;
    }

    public void draw(Graphics g) { // Drawing loop for the cell
        tick++;
        int currentSize = size; // For size changes

        if(state.getType().equals("INFECTED")) { // "Pulsate" if the cell is infected
            currentSize = (int) (size + Math.sin(tick * 0.1) * 5); // Sin wave for the pulsing
        }

        g.setColor(state.getCellColor());
        
        int offset = (currentSize - size) / 2;
        g.fillOval((int) x - offset, (int) y - offset, currentSize, currentSize);

        g.setColor(Color.BLACK);
        g.drawOval((int) x-offset, (int) y-offset, currentSize, currentSize);
    }

    public void onCollision(Cell opponent) { // change the cell states depending on the type of reaction
        CellState oppNewState = opponent.state.reactWith(this.getState());
        CellState thisNewState = this.state.reactWith(opponent.getState());
        changeState(thisNewState);
        opponent.changeState(oppNewState);
    }

    public void move(int panelMaxWidth, int panelMaxHeight, int panelMinWidth, int panelMinHeight) { // Move logic
        if (panelMaxWidth <= 0 || panelMaxHeight <= 0) {
            return; 
        }
        x += velX;
        y += velY;

        if (x <= panelMinWidth) {
            x = panelMinWidth;
            velX = -velX;
        } else if (x + size >= panelMaxWidth) {
            x = panelMaxWidth - size;
            velX = -velX;
        }

        if (y <= panelMinHeight) {
            y = panelMinHeight;
            velY = -velY;
        } else if (y + size >= panelMaxHeight) {
            y = panelMaxHeight - size;
            velY = -velY;
        }
    }

    public boolean collidesWith(Cell other) { // Collision logic
        int radius = size / 2;
        double centXA = x + radius;
        double centYA = y + radius;
        double centXB = other.x + radius;
        double centYB = other.y + radius;

        if (Math.hypot(centXB - centXA, centYB - centYA) <= size) {
            return true;
        }
        return false;
    }

}
