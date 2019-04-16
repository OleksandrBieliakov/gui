package gui.project1;

import java.awt.*;

public class DisplayPanel extends DrawingPanel {

    private DrawingFrame drawingFrame;

    DisplayPanel(DrawingFrame drawingFrame) {
        this.drawingFrame = drawingFrame;
    }

    private void update() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {
        }
        checkFile(drawingFrame);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics currentGraphics) {
        super.paintComponent(currentGraphics);
        update();
    }

}
