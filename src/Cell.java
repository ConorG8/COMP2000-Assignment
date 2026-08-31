import java.awt.Color;
import java.awt.Graphics;

// Cell Class
public class Cell {
    private double x;
    private double y;
    private double velX;
    private double velY;
    public double speed = Settings.CELL_SPEED;
    private int size = Settings.CELL_SIZE;

    private double speedMultiplier = 1.0;
    private double sizeMultiplier = 1.0;
    private int id;
    private CellState state;
    private Color color; // Default color for the cell
    public boolean isMutated = false; // Flag to indicate if the cell is mutated
    public int resistance = 0; // Resistance level for the cell

    private boolean collisionEnabled = Settings.hasCollision; // Collision enabled or not

    private int tick = 0;

    public Cell(double startX, double startY, int id, CellState initialState) {
        this.x = startX;
        this.y = startY;
        this.id = id;
        this.state = initialState;
        this.color = initialState.getCellColor(); // Set color based on initial state
        this.velX = ((Math.random() * 3)-1) * speed;
        this.velY = ((Math.random() * 3)-1) * speed;
    }
    
    public Cell(double startX, double startY, int id, CellState initialState, Color color, boolean isMutated) {
        this.x = startX;
        this.y = startY;
        this.id = id;
        this.state = initialState;
        this.color = color;
        this.isMutated = isMutated;
        this.velX = ((Math.random() * 3)-1) * speed;
        this.velY = ((Math.random() * 3)-1) * speed;
    }    

    public double getX() { return x; }
    public double getY() { return y; }
    public int getId() { return id; }
    public CellState getState() { return state; }
    public int getSize() {
    return (int) Math.round(size * sizeMultiplier);
     }

    public void changeState(CellState newState){
        this.state = newState;
    }

    public void draw(Graphics g) { // Drawing loop for the cell
        tick++;
        int currentSize = getSize(); // For size changes

        if(state.getType().equals("INFECTED")) { // "Pulsate" if the cell is infected
            currentSize = (int) (getSize() + Math.sin(tick * 0.1) * 5); // Sin wave for the pulsing
        }

        g.setColor(state.getCellColor());
        
        int offset = (currentSize - size) / 2;
        g.fillOval((int) x - offset, (int) y - offset, currentSize, currentSize);

        if(isMutated) { // Draw a border if the cell is mutated
            g.setColor(Color.YELLOW);
        }
        else {
            g.setColor(Color.BLACK);
        }
        g.drawOval((int) x-offset, (int) y-offset, currentSize, currentSize);
    }

    public void onCollision(Cell opponent) { // change the cell states depending on the type of reaction
        CellState oppNewState = opponent.state.reactWith(this.getState());
        CellState thisNewState = this.state.reactWith(opponent.getState());
        changeState(thisNewState);
        opponent.changeState(oppNewState);
        if (collisionEnabled) {
            bounceOff(opponent);
        }
    }

    public void bounceOff(Cell opponent) { // Bounce off logic
        double tempVelX = this.velX;
        double tempVelY = this.velY;

        this.velX = opponent.velX;
        this.velY = opponent.velY;

        opponent.velX = tempVelX;
        opponent.velY = tempVelY;
        
        // Separate cells to prevent sticking
        double dx = opponent.x - this.x;
        double dy = opponent.y - this.y;
        double distance = Math.hypot(dx, dy);
        
        if (distance < size) {
            double overlap = size - distance;
            double separationX = (dx / distance) * (overlap / 2 + 1);
            double separationY = (dy / distance) * (overlap / 2 + 1);
            
            this.x -= separationX;
            this.y -= separationY;
            opponent.x += separationX;
            opponent.y += separationY;
        }
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
        } else if (x + getSize() >= panelMaxWidth) {
            x = panelMaxWidth - getSize();
            velX = -velX;
        }

        if (y <= panelMinHeight) {
            y = panelMinHeight;
            velY = -velY;
        } else if (y + getSize() >= panelMaxHeight) {
            y = panelMaxHeight - getSize();
            velY = -velY;
        }
    }

   public boolean collidesWith(Cell other) { // Collision logic

    int radiusA = getSize() / 2;
    int radiusB = other.getSize() / 2;

    double centXA = x + radiusA;
    double centYA = y + radiusA;

    double centXB = other.x + radiusB;
    double centYB = other.y + radiusB;

    double distance = Math.hypot(
        centXB - centXA,
        centYB - centYA
    );

    return distance <= radiusA + radiusB;
     }

    public void setSpeedMultiplier(double multiplier) {

    double speedChange = multiplier / speedMultiplier;

    velX *= speedChange;
    velY *= speedChange;

    speedMultiplier = multiplier;
     }


public void setSizeMultiplier(double multiplier) {

    sizeMultiplier = multiplier;
    }
}