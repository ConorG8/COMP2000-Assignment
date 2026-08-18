import java.awt.Color;

public class AntivirusState extends StateMachine{
    public String getType() { return "ANTIVIRUS"; }
    public Color getCellColor() { return new Color(0, 0, 255); }
}