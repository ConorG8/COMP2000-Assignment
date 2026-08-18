import java.awt.Color;

public class InfectedState extends StateMachine{
    @Override public String getType() { return "INFECTED"; }
    @Override public Color getCellColor() { return new Color(255, 0, 0); }
}