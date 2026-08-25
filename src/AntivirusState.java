import java.awt.Color;

public class AntivirusState extends StateMachine {
    @Override 
    public String getType(){ 
        return "ANTIVIRUS"; 
    }

    @Override 
    public Color getCellColor(){
        return new Color(0, 0, 255);
    }
}