import javax.swing.JFrame;

import java.awt.Graphics;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pandemic Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SimulationPanel()); 
        frame.pack();
        frame.setLocationRelativeTo(null);
        Graphics g = frame.getGraphics();

        frame.setVisible(true);
    }
}

