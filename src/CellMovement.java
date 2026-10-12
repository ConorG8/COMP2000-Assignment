/* import java.util.Random;

public class CellMovement {
    private double x;
    private double y;
    private double velX;
    private double velY;
    private double speed;
    private double speedMultiplier = 1.0;

    public CellMovement(double startX, double startY, double baseSpeed) {
        this.x = startX;
        this.y = startY;
        this.speed = baseSpeed;
        
        // Randomise initial velocity vectors
        this.velX = ((Math.random() * 3) - 1) * (this.speed * speedMultiplier);
        this.velY = ((Math.random() * 3) - 1) * (this.speed * speedMultiplier);
    }

    public void updatePosition() {
        this.x += velX;
        this.y += velY;
    }

    // Getters and Setters
    public double getX() { return x; }
    public double getY() { return y; }
    public double getVelX() { return velX; }
    public double getVelY() { return velY; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setVelocity(double velX, double velY) {
        this.velX = velX;
        this.velY = velY;
    }
    public void setSpeedMultiplier(double multiplier) { this.speedMultiplier = multiplier; }
}
    */
