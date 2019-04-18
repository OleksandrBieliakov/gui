package gui.project1v2;

import javax.swing.*;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static int askNumber() {
        String s = JOptionPane.showInputDialog(null, "Enter a number of figures you want to generate");
        if (s == null) System.exit(0);
        return Integer.parseInt(s);
    }

    private static void runPantingModule(File file, int figuresNeed) {
        DrawingFrame paintingModule = new DrawingFrame(file, true);
        int figuresGenerated = 0;
        while (figuresGenerated < figuresNeed) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            paintingModule.generateFigure();
            figuresGenerated++;
            paintingModule.refresh();
        }
    }

    private static void runBothModules(File file, int figuresNeeded) {
        DrawingFrame paintingModule = new DrawingFrame(file, true);
        DrawingFrame displayModule = new DrawingFrame(file, false);

        AtomicInteger figuresGenerated = new AtomicInteger(0);

        while (figuresGenerated.get() < figuresNeeded) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Thread generateThenRead = new Thread(() -> {
                paintingModule.generateFigure();
                figuresGenerated.getAndIncrement();
                displayModule.readFile();
            });
            Thread refreshPM = new Thread(paintingModule::refresh);
            Thread refreshDM = new Thread(displayModule::refresh);

            generateThenRead.start();
            try {
                generateThenRead.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            refreshDM.start();
            refreshPM.start();
        }
    }

    public static void main(String[] args) {

        Object[] options = {"Painting", "Display", "Both", "Cancel"};
        int selected = JOptionPane.showOptionDialog(null, "Which module do you want to open?", "Module select", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[3]);
        if (selected == 3) System.exit(0);

        File dir = new File("data");
        if (!dir.isDirectory()) {
            boolean created = dir.mkdir();
            if (!created) {
                System.err.println("Cannot create data dir");
                System.exit(1);
            }
        }
        File file = new File(dir, "figures");

        if (selected == 0) runPantingModule(file, askNumber());
        else if (selected == 1) new DrawingFrame(file, false);
        else runBothModules(file, askNumber());

    }

}
