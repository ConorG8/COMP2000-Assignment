import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pandemic Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SimulationPanel());   // you'll build this in step 5
        frame.pack();
        frame.setLocationRelativeTo(null);
        Graphics g = frame.getGraphics();

        frame.setVisible(true);
    }
}

