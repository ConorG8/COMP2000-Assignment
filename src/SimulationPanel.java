import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SimulationPanel extends JPanel implements ActionListener{
    private final List<Cell> cells = new ArrayList<>();
    private Timer timer;
    public int simScreenX;
    public int simScreenY;
    public int offset;
    public int statScreenX = 200;
    private boolean cellsCreated = false;

    public SimulationPanel() {
        this.setPreferredSize(new Dimension(1200, 800));
        this.setBackground(Color.black);
        offset = 10;
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateDimensions();
            }
        });

        timer = new Timer(16, this);
        timer.start();
        
    }
    
    public void updateDimensions() {
        simScreenX = this.getWidth() - statScreenX - offset;
        simScreenY = this.getHeight() - offset;
        
        // Create cells once dimensions are available
        if (!cellsCreated && simScreenX > 0 && simScreenY > 0) {
            createCells();
            cellsCreated = true;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 

        g.setColor(Color.BLACK);

        drawSimBorder(g);

        drawStats(g);

        for (Cell cell : cells) {
            cell.draw(g);
        }
    }

    public void createCells() { // Create each cell, and add to the list with the given variables.
        for (int i = 0; i < 100; i++) {
            if (i < 80) {
                // Minus 10 from the width and height of the screen to account for the diameter of the cell
                cells.add(new Cell(Math.random() * (simScreenX - offset), Math.random() * (simScreenY - offset), i, NeutralState.INSTANCE)); 
            } 
            else if(i >= 80 && i <= 90){
                cells.add(new Cell(Math.random() * (simScreenX - offset), Math.random() * (simScreenY - offset), i, InfectedState.INSTANCE));
            }
            else {
                cells.add(new Cell(Math.random() * (simScreenX - offset), Math.random() * (simScreenY - offset), i, AntivirusState.INSTANCE));
            }
        }
    }

    public void drawSimBorder(Graphics g){
        // Sim screen borders
        g.setColor(Color.BLACK);
        g.drawLine(simScreenX, offset, simScreenX, simScreenY);
        g.drawLine(offset, offset, offset, simScreenY);
        g.drawLine(offset, offset, simScreenX, offset);
        g.drawLine(offset, simScreenY, simScreenX, simScreenY);
        // Sim screen background
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(offset, offset, simScreenX - offset, simScreenY - offset);
    }

    public void drawStats(Graphics g){
        int neutralCount = 0;
        int infectedCount = 0;
        int antivirusCount = 0;
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString("Total Cells: " + cells.size(), simScreenX + offset, offset * 3);

        for(Cell c : cells){
            if(c.getState().getType().equals("NEUTRAL")){
                neutralCount++;
            }
            if(c.getState().getType().equals("INFECTED")){
                infectedCount++;
            }
            if(c.getState().getType().equals("ANTIVIRUS")){
                antivirusCount++;
            }
        }
        g.setColor(NeutralState.INSTANCE.getCellColor());
        g.drawString("Neutral Cells: " + neutralCount, simScreenX + offset, offset * 6);
        g.setColor(InfectedState.INSTANCE.getCellColor());
        g.drawString("Infected Cells: " + infectedCount, simScreenX + offset, offset * 9);
        g.setColor(AntivirusState.INSTANCE.getCellColor());
        g.drawString("Antivirus Cells: " + antivirusCount, simScreenX + offset, offset * 12);
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