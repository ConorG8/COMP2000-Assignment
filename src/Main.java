import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pandemic Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SimulationPanel());
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        frame.setVisible(true);

        Settings.CELL_SIZE = 20; // Set the cell size
        Settings.CELL_SPEED = 1.0; // Set the cell speed
    }
}

