import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.tools.Tool;

public class Window extends Frame {

    public Window() {
        Frame currentFrame = this;
        currentFrame.setTitle("Pandemic Simulation");
        currentFrame.setSize(500, 500);
        currentFrame.setLayout(new GridBagLayout());
        currentFrame.setLocationRelativeTo(null);

        Panel panel = new Panel();
        panel.setBackground(Color.gray);
        panel.setPreferredSize(new Dimension(400, 400));

        // Add panel to frame
        currentFrame.add(panel);

        // Window closing event
        this.addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    //currentFrame.dispose();
                    System.exit(0);
                }
            }
        );

    }
    

}