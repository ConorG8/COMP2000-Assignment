
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;

public class Settings {
    public static int CELL_SIZE = 20;
    public static double CELL_SPEED = 1.0;
    public static int CELL_COUNT = 100;
    public static int INFECTED_COUNT = 1;
    public static int ANTIVIRUS_COUNT = 1;
    public static boolean hasCollision = true;

    public JPanel settingsPanel() {
        // Create a settings panel with sliders for cell size and speed
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridLayout(6, 3, 5, 5));
        settingsPanel.setBackground(Color.DARK_GRAY);
        settingsPanel.setOpaque(true);

        JLabel sizeLabel = new JLabel("Size");
        sizeLabel.setForeground(Color.WHITE);
        JSlider sizeSlider = new JSlider(5, 50, Settings.CELL_SIZE);
        sizeSlider.setBackground(Color.WHITE);
        JLabel sizeValueLabel = new JLabel(String.valueOf(Settings.CELL_SIZE));
        sizeValueLabel.setForeground(Color.WHITE);
        sizeSlider.addChangeListener(e -> {
            Settings.CELL_SIZE = sizeSlider.getValue();
            sizeValueLabel.setText(String.valueOf(Settings.CELL_SIZE));
        });

        JLabel speedLabel = new JLabel("Speed");
        speedLabel.setForeground(Color.WHITE);
        JSlider speedSlider = new JSlider(1, 100, (int) (Settings.CELL_SPEED * 10));
        speedSlider.setBackground(Color.WHITE);
        JLabel speedValueLabel = new JLabel(String.valueOf(Settings.CELL_SPEED));
        speedValueLabel.setForeground(Color.WHITE);
        speedSlider.addChangeListener(e -> {
            Settings.CELL_SPEED = speedSlider.getValue() / 10.0;
            speedValueLabel.setText(String.valueOf(Settings.CELL_SPEED));
        });

        JLabel countLabel = new JLabel("Count");
        countLabel.setForeground(Color.WHITE);
        JSlider countSlider = new JSlider(10, 200, Settings.CELL_COUNT);
        countSlider.setBackground(Color.WHITE);
        JLabel countValueLabel = new JLabel(String.valueOf(Settings.CELL_COUNT));
        countValueLabel.setForeground(Color.WHITE);
        countSlider.addChangeListener(e -> {
            Settings.CELL_COUNT = countSlider.getValue();
            countValueLabel.setText(String.valueOf(Settings.CELL_COUNT));
        });

        JLabel infectedCountLabel = new JLabel("InfCells");
        infectedCountLabel.setForeground(Color.WHITE);
        JSlider infectedCountSlider = new JSlider(1, 20, Settings.INFECTED_COUNT);
        infectedCountSlider.setBackground(Color.WHITE);
        JLabel infectedCountValueLabel = new JLabel(String.valueOf(Settings.INFECTED_COUNT));
        infectedCountValueLabel.setForeground(Color.WHITE);
        infectedCountSlider.addChangeListener(e -> {
            Settings.INFECTED_COUNT = infectedCountSlider.getValue();
            infectedCountValueLabel.setText(String.valueOf(Settings.INFECTED_COUNT));
        });

        JLabel antivirusCountLabel = new JLabel("AntiCells");
        antivirusCountLabel.setForeground(Color.WHITE);
        JSlider antivirusCountSlider = new JSlider(1, 20, Settings.ANTIVIRUS_COUNT);
        antivirusCountSlider.setBackground(Color.WHITE);
        JLabel antivirusCountValueLabel = new JLabel(String.valueOf(Settings.ANTIVIRUS_COUNT));
        antivirusCountValueLabel.setForeground(Color.WHITE);
        antivirusCountSlider.addChangeListener(e -> {
            Settings.ANTIVIRUS_COUNT = antivirusCountSlider.getValue();
            antivirusCountValueLabel.setText(String.valueOf(Settings.ANTIVIRUS_COUNT));
        });

        JLabel collisionLabel = new JLabel("Collision");
        collisionLabel.setForeground(Color.WHITE);
        JToggleButton collisionToggle = new JToggleButton(Settings.hasCollision ? "On" : "Off");
        collisionToggle.setBackground(Color.WHITE);
        collisionToggle.addActionListener(e -> {
            Settings.hasCollision = !Settings.hasCollision;
            collisionToggle.setText(Settings.hasCollision ? "On" : "Off");
        });

        settingsPanel.add(sizeLabel);
        settingsPanel.add(sizeSlider);
        settingsPanel.add(sizeValueLabel);
        settingsPanel.add(speedLabel);
        settingsPanel.add(speedSlider);
        settingsPanel.add(speedValueLabel);
        settingsPanel.add(countLabel);
        settingsPanel.add(countSlider);
        settingsPanel.add(countValueLabel);
        settingsPanel.add(infectedCountLabel);
        settingsPanel.add(infectedCountSlider);
        settingsPanel.add(infectedCountValueLabel);
        settingsPanel.add(antivirusCountLabel);
        settingsPanel.add(antivirusCountSlider);
        settingsPanel.add(antivirusCountValueLabel);
        settingsPanel.add(collisionLabel);
        settingsPanel.add(collisionToggle);

        settingsPanel.doLayout();

        return settingsPanel;
    }

}