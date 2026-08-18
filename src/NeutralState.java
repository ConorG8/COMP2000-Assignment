import java.awt.Color;

public class NeutralState extends StateMachine{
    public String getType() { return "NEUTRAL"; }
    public Color getCellColor() { return new Color(0, 255, 0); }
}