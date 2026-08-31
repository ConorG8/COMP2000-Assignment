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
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SimulationPanel extends JPanel implements ActionListener {
    private List<Cell> cells = new ArrayList<>();
    private Timer timer;
    private int deadCellCount;
    private int mutatedCellCount;
    public int simScreenX;
    public int simScreenY;
    public int offset;
    public int statScreenX = 200;
    private boolean cellsCreated = false;
    private JButton resetButton;
    private boolean buttonCreated = false;

    public SimulationPanel() {
        this.setPreferredSize(new Dimension(1200, 800));
        this.setLayout(null);
        deadCellCount = 0;
        mutatedCellCount = 0;
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

    private void createResetButton() {
        resetButton = new JButton("Reset Simulation");
        resetButton.addActionListener((ActionEvent e) -> {
            resetSimulation();
        });
        this.add(resetButton);
    }

    public void resetSimulation() {
        cells.clear();
        cellsCreated = false;
        deadCellCount = 0;
        mutatedCellCount = 0;
        updateDimensions();
        drawStats(this.getGraphics());
    }

    public void updateDimensions() {
        simScreenX = this.getWidth() - statScreenX - offset;
        simScreenY = this.getHeight() - offset;

        // Create cells once dimensions are available
        if (!cellsCreated && simScreenX > 0 && simScreenY > 0) {
            createCells();
            cellsCreated = true;
        }

        // Create button once dimensions are available
        if (!buttonCreated && simScreenX > 0 && simScreenY > 0) {
            createResetButton();
            buttonCreated = true;
        }

        // Reposition button based on window size
        if (resetButton != null) {
            resetButton.setBounds(simScreenX + offset, offset * 27, 180, 50);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        g.setColor(Color.BLACK);

        drawSimBorder(g);

        drawStats(g);

        for (Cell cell : cells) {
            cell.draw(g);
            randomDeath(cell, cell.getId());
            randomDuplication(cell);
        }
    }

    public void createCells() { // Create each cell, and add to the list with the given variables.
        for (int i = 0; i < 100; i++) {
            if (i < 80) {
                // Minus 10 from the width and height of the screen to account for the diameter
                // of the cell
                cells.add(new Cell(Math.random() * (simScreenX - offset), Math.random() * (simScreenY - offset), i,
                        NeutralState.INSTANCE));
            } else if (i >= 80 && i <= 90) {
                cells.add(new Cell(Math.random() * (simScreenX - offset), Math.random() * (simScreenY - offset), i,
                        InfectedState.INSTANCE));
            } else {
                cells.add(new Cell(Math.random() * (simScreenX - offset), Math.random() * (simScreenY - offset), i,
                        AntivirusState.INSTANCE));
            }
        }
    }

    public void drawSimBorder(Graphics g) {
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

    public void drawStats(Graphics g) {
        int neutralCount = 0;
        int infectedCount = 0;
        int antivirusCount = 0;
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g.setColor(Color.WHITE);
        g.drawString("Pandemic", simScreenX + offset, offset * 3);
        g.drawString("Simulator", simScreenX + offset, offset * 6);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        g.drawString("Total Cells: " + cells.size(), simScreenX + offset, offset * 10);

        for (Cell c : cells) {
            if (c.getState().getType().equals("NEUTRAL")) {
                neutralCount++;
            }
            if (c.getState().getType().equals("INFECTED")) {
                infectedCount++;
            }
            if (c.getState().getType().equals("ANTIVIRUS")) {
                antivirusCount++;
            }
        }
        g.setColor(NeutralState.INSTANCE.getCellColor());
        g.drawString("Neutral Cells: " + neutralCount, simScreenX + offset, offset * 13);
        g.setColor(InfectedState.INSTANCE.getCellColor());
        g.drawString("Infected Cells: " + infectedCount, simScreenX + offset, offset * 16);
        g.setColor(AntivirusState.INSTANCE.getCellColor());
        g.drawString("Antivirus Cells: " + antivirusCount, simScreenX + offset, offset * 19);
        g.setColor(Color.DARK_GRAY);
        g.drawString("Dead Cells: " + deadCellCount, simScreenX + offset, offset * 22);
        g.setColor(Color.YELLOW);
        g.drawString("Mutated Cells: " + mutatedCellCount, simScreenX + offset, offset * 25);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Cell cell : cells) { // For each cell, move
            cell.move(simScreenX, simScreenY, offset, offset);
        }

        for (int i = 0; i < cells.size(); i++) { // Check cell collisions
            for (int j = i + 1; j < cells.size(); j++) {
                Cell a = cells.get(i);
                Cell b = cells.get(j);

                if (a.collidesWith(b)) {
                    a.onCollision(b);
                }
            }
        }

        repaint(); // draw next frame
    }

    public void randomDeath(Cell c, int id) {
        double randomNum = Math.random();
        if (randomNum < 0.0001) { // small chance to die
            cells.remove(id);
            deadCellCount++;
        }
    }

    public void randomDuplication(Cell c){
        double randomNum = Math.random();
        if(c.getState().equals(InfectedState.INSTANCE) || c.getState().equals(AntivirusState.INSTANCE)){ // If antivirus or infected
            if(randomNum < 0.0002){ // small chance to duplicate
                Cell newCell = c;
                cells.add(newCell);
                mutatedCellCount++;
            }
        }
    }
}