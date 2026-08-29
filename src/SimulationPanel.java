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
    public int screenX;
    private int screenY;

    public SimulationPanel() {
        this.setPreferredSize(new Dimension(1000, 800));
        this.setBackground(Color.LIGHT_GRAY);
        screenX = this.getPreferredSize().width;
        screenY = this.getPreferredSize().height;

        createCells();

        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 

        g.setColor(Color.BLACK);

        for (Cell cell : cells) {
            cell.draw(g);
        }
    }

    public void createCells() { // Create each cell, and add to the list with the given variables.
        for (int i = 0; i < 100; i++) {
            if (i < 80) {
                // 990 and 790 for the height and width, inset by 10 pixels each.
                // TODO: Make them screen width and height variables, for cleaner code. (should be implemented now)

                // Minus 10 from the width and height of the screen to account for the diameter of the cell

                cells.add(new Cell(Math.random() * (screenX - 10), Math.random() * (screenY - 10), i, new NeutralState())); 
            } 
            else if(i >= 80 && i <= 90){
                cells.add(new Cell(Math.random() * (screenX - 10), Math.random() * (screenY - 10), i, new InfectedState()));
            }
            else {
                cells.add(new Cell(Math.random() * (screenX - 10), Math.random() * (screenY - 10), i, new AntivirusState()));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Cell cell : cells) { // For each cell, move
            cell.move(getWidth(), getHeight());
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