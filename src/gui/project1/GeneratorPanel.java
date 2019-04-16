package gui.project1;

import java.awt.*;

public class GeneratorPanel extends DrawingPanel {

    private DrawingFrame drawingFrame;
    private int figuresNeeded;
    private int figuresNumber = 0;

    GeneratorPanel(DrawingFrame drawingFrame, int figuresNeeded) {
        this.drawingFrame = drawingFrame;
        this.figuresNeeded = figuresNeeded;
    }

    private void generateFigure() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
        Figure figure = new Figure();
        super.addFigureToList(figure);
        drawingFrame.saveFigure(figure);
        figuresNumber++;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics currentGraphics) {
        super.paintComponent(currentGraphics);
        if (figuresNumber < figuresNeeded) {
            generateFigure();
        }
    }

}