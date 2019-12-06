package pointed.project1;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PaintingThread extends Thread {
    private final DrawingFrame paintingModule;
    private final DrawingFrame displayModule;
    private final int figuresNeeded;
    private AtomicInteger figuresGenerated;
    private final int rate;

    PaintingThread(DrawingFrame paintingModule, DrawingFrame displayModule, int figuresNeeded, AtomicInteger figuresGenerated, int rate) {
        this.paintingModule = paintingModule;
        this.displayModule = displayModule;
        this.figuresNeeded = figuresNeeded;
        this.figuresGenerated = figuresGenerated;
        this.rate = rate;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(rate);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (figuresGenerated.get() < figuresNeeded) {
            SwingUtilities.invokeLater(() -> {
                if (figuresGenerated.get() < figuresNeeded) {
                    paintingModule.generateFigure();
                    figuresGenerated.getAndIncrement();
                }
            });
            SwingUtilities.invokeLater(paintingModule::refresh);
            SwingUtilities.invokeLater(displayModule::readFile);
            SwingUtilities.invokeLater(displayModule::refresh);
            try {
                Thread.sleep(rate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
