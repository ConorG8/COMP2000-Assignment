import java.awt.Color;

public class InfectedState extends StateMachine{
    public String getType() { return "INFECTED"; }
    public Color getCellColor() { return new Color(255, 0, 0); }
}