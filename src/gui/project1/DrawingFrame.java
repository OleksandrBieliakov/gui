package gui.project1;

import javax.swing.*;
import java.util.ArrayList;

class DrawingFrame extends JFrame {

    DrawingFrame() {
        add(new DrawingPanel(this));
        setupFrame();
    }

    private void setupFrame() {
        this.setTitle("Drawing panel");
        this.setContentPane(new DrawingPanel(this));
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
