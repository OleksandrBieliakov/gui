package gui.project1;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class DrawingFrame extends JFrame {

    private File file;

    DrawingFrame(int figuresNeeded, File file) {
        this.file = file;
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
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.append(figure.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
