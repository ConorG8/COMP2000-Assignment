import java.awt.Color;

public class NeutralState extends StateMachine{
    @Override 
    public String getType(){
        return "NEUTRAL";
    }
    
    @Override 
    public Color getCellColor(){
        return new Color(0, 255, 0);
    }
}