import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationPanel extends JPanel implements ActionListener{
    private final List<Cell> cells = new ArrayList<>();
    private Timer timer;
    public int simScreenX;
    public int simScreenY;
    public int offset;
    public int statScreenX = 400;

    public SimulationPanel() {
        this.setPreferredSize(new Dimension(1200, 800));
        this.setBackground(Color.black);
        offset = 10;
        simScreenX = this.getPreferredSize().width - statScreenX - offset;
        simScreenY = this.getPreferredSize().height - offset;

        createCells();

        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 

        g.setColor(Color.BLACK);

        // Sim screen borders
        g.drawLine(simScreenX, offset, simScreenX, simScreenY);
        g.drawLine(offset, offset, offset, simScreenY);
        g.drawLine(offset, offset, simScreenX, offset);
        g.drawLine(offset, simScreenY, simScreenX, simScreenY);
        g.setColor(Color.WHITE);
        g.fillRect(offset, offset, simScreenX - offset, simScreenY - offset);

        for (Cell cell : cells) {
            cell.draw(g);
        }
    }

    public void createCells() { // Create each cell, and add to the list with the given variables.
        for (int i = 0; i < 100; i++) {
            if (i < 80) {
                // Minus 10 from the width and height of the screen to account for the diameter of the cell
                cells.add(new Cell(Math.random() * (simScreenX - offset), Math.random() * (simScreenY - offset), i, new NeutralState())); 
            } 
            else if(i >= 80 && i <= 90){
                cells.add(new Cell(Math.random() * (simScreenX - offset), Math.random() * (simScreenY - offset), i, new InfectedState()));
            }
            else {
                cells.add(new Cell(Math.random() * (simScreenX - offset), Math.random() * (simScreenY - offset), i, new AntivirusState()));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Cell cell : cells) { // For each cell, move
            cell.move(simScreenX, simScreenY, offset, offset);
        }

        for (int i = 0; i < cells.size(); i++) { // Check cell collisions
            for (int j = i+1; j < cells.size(); j++) {
                Cell a = cells.get(i);
                Cell b = cells.get(j);

                if (a.collidesWith(b)) {
                    a.onCollision(b);
                }
            }
        }

        repaint(); // draw next frame
    }
}