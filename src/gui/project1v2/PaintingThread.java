package gui.project1v2;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class PaintingThread extends Thread {
    private final DrawingFrame paintingModule;
    private final int figuresNeeded;
    private AtomicInteger figuresGenerated;
    private AtomicBoolean canUseFile;

    PaintingThread(DrawingFrame paintingModule, int figuresNeeded, AtomicInteger figuresGenerated, AtomicBoolean canUseFile) {
        this.paintingModule = paintingModule;
        this.figuresNeeded = figuresNeeded;
        this.figuresGenerated = figuresGenerated;
        this.canUseFile = canUseFile;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int sleepTime = 0;
        while (figuresGenerated.get() < figuresNeeded) {
            while (!canUseFile.get()) {
                try {
                    Thread.sleep(5);
                    sleepTime += 5;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (figuresGenerated.get() < figuresNeeded) {
                canUseFile.set(false);
                paintingModule.generateFigure();
                figuresGenerated.getAndIncrement();
                paintingModule.refresh();
                canUseFile.set(true);
            }
            try {
                Thread.sleep(1000 - sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sleepTime = 0;
        }
    }
}
