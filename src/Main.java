import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pandemic Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SimulationPanel simPanel = new SimulationPanel();
        frame.add(simPanel, BorderLayout.CENTER);
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        frame.setVisible(true);
    }
}

