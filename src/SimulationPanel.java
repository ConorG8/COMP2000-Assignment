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
import javax.swing.JCheckBox;

public class SimulationPanel extends JPanel implements ActionListener {
    private final List<Cell> cells = new ArrayList<>();
    private SeasonManager seasonManager;
    private long lastUpdateTime;
    private Color simulationBackground = Color.LIGHT_GRAY;
    private JCheckBox seasonsCheckBox;
    private Timer timer;
    public int simScreenX;
    public int simScreenY;
    public int offset;
    public int statScreenX = 200;
    private boolean cellsCreated = false;
    private JButton resetButton;
    private boolean buttonCreated = false;
    private JPanel settingsPanel;
    private boolean settingsCreated = false;
    private int cellCount = Settings.CELL_COUNT; // Total number of cells in the simulation
    private int deadCellCount;
    private int mutatedCellCount;
    

    public SimulationPanel() {
        this.setPreferredSize(new Dimension(1200, 800));
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        deadCellCount = 0;
        mutatedCellCount = 0;
        offset = 10;

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateDimensions();
            }
        });

        seasonManager = new SeasonManager(this);

    lastUpdateTime = System.currentTimeMillis();

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
        cellCount = Settings.CELL_COUNT;
        cells.clear();
        cellsCreated = false;
        deadCellCount = 0;
        mutatedCellCount = 0;
        seasonManager.reset();
        lastUpdateTime = System.currentTimeMillis();
        updateDimensions();
        repaint();
    }

    private void createSeasonsCheckBox() {
        seasonsCheckBox = new JCheckBox("Enable Seasons", true);

        seasonsCheckBox.addActionListener(e -> {
            seasonManager.setEnabled(seasonsCheckBox.isSelected());
            repaint();
        });

        this.add(seasonsCheckBox);
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
        
        // Create settings once dimensions are available
        if (!settingsCreated && simScreenX > 0 && simScreenY > 0) {
            settingsPanel = new Settings().settingsPanel();
            settingsPanel.setBounds(simScreenX + offset, offset * 26, 180, 120);
            this.add(settingsPanel);
            settingsPanel.revalidate();
            settingsPanel.repaint();
            settingsCreated = true;
        }
        
        //Create Seasonal Checkbox once dimensions are available
        if (seasonsCheckBox == null && simScreenX > 0 && simScreenY > 0) {
            createSeasonsCheckBox();
        }
        // Reposition button based on window size
        if (resetButton != null) {
            resetButton.setBounds(simScreenX + offset, offset * 43, 180, 50);
        }
        
        // Reposition settings based on window size
        if (settingsPanel != null) {
            settingsPanel.setBounds(simScreenX + offset, offset * 26, 180, 120);
            settingsPanel.revalidate();
            settingsPanel.repaint();
        }

        // Reposition Checkbox based on window size
        if (seasonsCheckBox != null) {
            seasonsCheckBox.setBounds(simScreenX + offset, offset * 39, 170, 25);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(simulationBackground);

        drawSimBorder(g);

        drawStats(g);

        for (Cell cell : cells) {
            cell.draw(g);
        }
    }

    public void createCells() { // Create each cell, and add to the list with the given variables.
        for (int i = 0; i < cellCount; i++) {
            // Generate random position within the simulation area (accounting for offset and cell size)
            double randomX = offset + Math.random() * (simScreenX - 2 * offset - 20);
            double randomY = offset + Math.random() * (simScreenY - 2 * offset - 20);
            
            if (i < cellCount * 0.8) { // 80% Neutral, 10% Infected, 10% Antivirus
                cells.add(new Cell(randomX, randomY, i, NeutralState.INSTANCE)); 
            } 
            else if(i >= cellCount * 0.8 && i < cellCount * 0.9){
                cells.add(new Cell(randomX, randomY, i, InfectedState.INSTANCE));
            }
            else {
                cells.add(new Cell(randomX, randomY, i, AntivirusState.INSTANCE));
            }
        }
    }

    public void drawSimBorder(Graphics g) {
        // Sim screen borders
        g.setColor(simulationBackground);
        g.fillRect(offset, offset, simScreenX - offset, simScreenY - offset);
        g.setColor(Color.BLACK);
        g.drawLine(simScreenX, offset, simScreenX, simScreenY);
        g.drawLine(offset, offset, offset, simScreenY);
        g.drawLine(offset, offset, simScreenX, offset);
        g.drawLine(offset, simScreenY, simScreenX, simScreenY);
        // Sim screen background
        g.setColor(simulationBackground);
        g.fillRect(offset, offset, simScreenX - offset, simScreenY - offset);
    }

    public void drawStats(Graphics g) {
        int neutralCount = 0;
        int infectedCount = 0;
        int antivirusCount = 0;
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g.setColor(Color.BLACK);
        g.drawString("Season: " + seasonManager.getCurrentSeason(), 10, simScreenY - 35);
        g.drawString("Next Season: " + seasonManager.getSecondsRemaining() + "s", 10, simScreenY - 10);
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

    long currentTime = System.currentTimeMillis();

    long deltaTime = currentTime - lastUpdateTime;

    lastUpdateTime = currentTime;


    // Move each cell.
    for (Cell cell : cells) {

        cell.move(simScreenX, simScreenY, offset, offset);
    }


    // Check cell collisions.
    for (int i = 0; i < cells.size(); i++) {

        for (int j = i + 1; j < cells.size(); j++) {

            Cell a = cells.get(i);
            Cell b = cells.get(j);

            if (a.collidesWith(b)) {

                a.onCollision(b);
            }
        }
    }


    // Update the season system.
    seasonManager.update(cells, deltaTime);
    


    // Draw next frame.
    repaint();
    }

    /*public void randomDeath(Cell c, int id) {
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
                // Cell newCell = c;
                cells.add(new Cell(c.getX(), c.getY(), cells.size(), c.getState(), Color.ORANGE, true)); // Add a new cell with the same state and color
                mutatedCellCount++;
            }
        }
    }*/

         public void setSimulationBackground(Color background) {
            simulationBackground = background;
            repaint();
    }


    public void incrementDeadCellCount() {

        deadCellCount++;
     }


    public void incrementMutatedCellCount() {

        mutatedCellCount++;
     }

     @Override
    public void addNotify() {
        super.addNotify();
        javax.swing.SwingUtilities.invokeLater(this::updateDimensions);
    }  
}   