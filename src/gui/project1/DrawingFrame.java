package gui.project1;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class DrawingFrame extends JFrame {

    //private File file;
    private BufferedWriter bufferedWriter;

    DrawingFrame(int figuresNeeded, File file) {
        //this.file = file;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(new DrawingPanel(this, figuresNeeded));
        setupFrame();
    }

    private void setupFrame() {
        this.setTitle("Drawing panel");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    void saveFigure(Figure figure) {
        try {
            bufferedWriter.write(figure.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
