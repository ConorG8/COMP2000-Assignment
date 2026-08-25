import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationPanel extends JPanel {
    private final List<Cell> cells = new ArrayList<>();

    public SimulationPanel() {
        this.setPreferredSize(new Dimension(1000, 800));
        this.setBackground(Color.LIGHT_GRAY);
        createCells();
    }

    @Override
    protected void paintComponent(Graphics g) {
        
    }

    public void createCells(){
        for(int i = 0; i < 100; i++){
            if(i < 33){
                cells.add(new Cell(Math.random() * 990, Math.random() * 790, i, new NeutralState()));
            } 
            else if(i >= 33 && i <= 66){
                cells.add(new Cell(Math.random() * 990, Math.random() * 790, i, new InfectedState()));
            }
            else if(i > 66){
                cells.add(new Cell(Math.random() * 990, Math.random() * 790, i, new AntivirusState()));
            }
        }
    }
}