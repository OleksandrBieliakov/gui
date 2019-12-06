package pointed.project1;

import javax.swing.*;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static int askNumber() {
        String s = JOptionPane.showInputDialog(null, "Enter a number of figures you want to generate");
        if (s == null) System.exit(0);
        return Integer.parseInt(s);
    }

    private static void runPantingModule(File file, int figuresNeeded) {
        DrawingFrame paintingModule = new DrawingFrame(file, "Painting module", FramePositioning.valueOf("ONE_CENTER"));
        AtomicInteger figuresGenerated = new AtomicInteger();
        int rate = 1000;

        try {
            Thread.sleep(rate);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (figuresGenerated.get() < figuresNeeded) {
            SwingUtilities.invokeLater(() -> {
                paintingModule.generateFigure();
                figuresGenerated.getAndIncrement();
            });
            SwingUtilities.invokeLater(paintingModule::refresh);
            try {
                Thread.sleep(rate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void runBothModules(File file, int figuresNeeded) {
        DrawingFrame paintingModule = new DrawingFrame(file, "Painting module", FramePositioning.valueOf("TWO_LEFT"));
        DrawingFrame displayModule = new DrawingFrame(file, "Display module", FramePositioning.valueOf("TWO_RIGHT"));

        AtomicInteger figuresGenerated = new AtomicInteger(0);

        new PaintingThread(paintingModule, displayModule, figuresNeeded, figuresGenerated, 1000).start();
    }

    private static void runMerge(File file, int figuresNeeded) {
        DrawingFrame paintingModule1 = new DrawingFrame(file, "Painting module 1", FramePositioning.valueOf("THREE_LEFT"));
        DrawingFrame displayModule = new DrawingFrame(file, "Merging display module", FramePositioning.valueOf("THREE_CENTER"));
        DrawingFrame paintingModule2 = new DrawingFrame(file, "Painting module 2", FramePositioning.valueOf("THREE_RIGHT"));

        AtomicInteger figuresGenerated = new AtomicInteger(0);

        new PaintingThread(paintingModule1, displayModule, figuresNeeded, figuresGenerated, 1000).start();
        new PaintingThread(paintingModule2, displayModule, figuresNeeded, figuresGenerated, 1000).start();
    }

    public static void main(String[] args) {

        Object[] options = {"Painting", "Display", "Both", "MERGE", "Cancel"};
        int selected = JOptionPane.showOptionDialog(null, "Which module do you want to open?", "Module select", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[3]);
        if (selected == 4) System.exit(0);

        File dir = new File("data");
        if (!dir.isDirectory()) {
            boolean created = dir.mkdir();
            if (!created) {
                System.err.println("Cannot create data dir");
                System.exit(1);
            }
        }
        File file = new File(dir, "figures");

        switch (selected) {
            case 0:
                runPantingModule(file, askNumber());
                break;
            case 1:
                new DrawingFrame(file, "Display module", FramePositioning.valueOf("ONE_CENTER"));
                break;
            case 2:
                runBothModules(file, askNumber());
                break;
            case 3:
                runMerge(file, askNumber());
        }

    }

}
