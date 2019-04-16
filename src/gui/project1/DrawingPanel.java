package gui.project1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {

    private ArrayList<Figure> figures = new ArrayList<>();

    DrawingPanel() {
        setupPanel();
    }

    private void setupPanel() {
        this.setBackground(Color.DARK_GRAY);
    }

    void addFigureToList(Figure figure) {
        figures.add(figure);
    }

    void checkFile(DrawingFrame drawingFrame) {
        figures.addAll(drawingFrame.readFile(figures.size() * 7));
    }

    @Override
    protected void paintComponent(Graphics currentGraphics) {
        super.paintComponent(currentGraphics);
        Graphics2D mainGraphics = (Graphics2D) currentGraphics;

        int frameWidth = getWidth();
        int frameHeight = getHeight();
        double size;
        int x, y, width, height, x2, y2;
        double stroke;

        if (frameWidth < frameHeight) {
            stroke = frameWidth * 0.005;
        } else {
            stroke = frameHeight * 0.005;
        }

        for (Figure figure : figures) {
            mainGraphics.setColor(figure.getColor());
            size = figure.getSize();
            width = (int) (frameWidth / 16 + (size * frameWidth / 8));
            height = (int) (frameHeight / 16 + (size * frameHeight / 8));
            x = (int) ((frameWidth - width) * figure.getPositionX());
            y = (int) ((frameHeight - height) * figure.getPositionY());
            mainGraphics.setStroke(new BasicStroke((int) stroke));

            switch (figure.getType()) {
                case RECTANGLE:
                    mainGraphics.setStroke(new BasicStroke((int) stroke * 3));
                    mainGraphics.drawRect(x, y, width, height);
                    break;
                case ELLIPSE:
                    mainGraphics.setStroke(new BasicStroke((int) stroke * 3));
                    mainGraphics.drawOval(x, y, width, height);
                    break;
                case TRIANGLE_TOP:
                    mainGraphics.drawLine(x + width / 2, y, x2 = x + width, y2 = y + height);
                    mainGraphics.drawLine(x2, y2, x, y2);
                    mainGraphics.drawLine(x, y2, x + width / 2, y);
                    break;
                case TRIANGLE_RIGHT:
                    mainGraphics.drawLine(x, y, x2 = x + width, y2 = y + height / 2);
                    mainGraphics.drawLine(x2, y2, x, y2 = y + height);
                    mainGraphics.drawLine(x, y2, x, y);
                    break;
                case TRIANGLE_BOTTOM:
                    mainGraphics.drawLine(x, y, x2 = x + width, y);
                    mainGraphics.drawLine(x2, y, x2 = x + width / 2, y2 = y + height);
                    mainGraphics.drawLine(x2, y2, x, y);
                    break;
                case TRIANGLE_LEFT:
                    mainGraphics.drawLine(x + width, y, x2 = x + width, y2 = y + height);
                    mainGraphics.drawLine(x2, y2, x, y2 = y + height / 2);
                    mainGraphics.drawLine(x, y2, x + width, y);
                    break;
                case RHOMBUS:
                    mainGraphics.drawLine(x + width / 2, y, x2 = x + width, y2 = y + height / 2);
                    mainGraphics.drawLine(x2, y2, x2 = x + width / 2, y2 = y + height);
                    mainGraphics.drawLine(x2, y2, x, y2 = y + height / 2);
                    mainGraphics.drawLine(x, y2, x + width / 2, y);
                    break;
                case STAR:
                    int tmp1, tmp2;

                    // TRIANGLE_TOP
                    tmp1 = height;
                    height -= height / 4;
                    mainGraphics.drawLine(x + width / 2, y, x2 = x + width, y2 = y + height);
                    mainGraphics.drawLine(x2, y2, x, y2);
                    mainGraphics.drawLine(x, y2, x + width / 2, y);
                    height = tmp1;

                    // TRIANGLE_RIGHT
                    tmp1 = x;
                    tmp2 = width;
                    x += width / 4;
                    width -= width / 4;
                    mainGraphics.drawLine(x, y, x2 = x + width, y2 = y + height / 2);
                    mainGraphics.drawLine(x2, y2, x, y2 = y + height);
                    mainGraphics.drawLine(x, y2, x, y);
                    x = tmp1;
                    width = tmp2;

                    // TRIANGLE_BOTTOM
                    tmp1 = y;
                    tmp2 = height;
                    y += height / 4;
                    height -= height / 4;
                    mainGraphics.drawLine(x, y, x2 = x + width, y);
                    mainGraphics.drawLine(x2, y, x2 = x + width / 2, y2 = y + height);
                    mainGraphics.drawLine(x2, y2, x, y);
                    y = tmp1;
                    height = tmp2;

                    // TRIANGLE_LEFT
                    width -= width / 4;
                    mainGraphics.drawLine(x + width, y, x2 = x + width, y2 = y + height);
                    mainGraphics.drawLine(x2, y2, x, y2 = y + height / 2);
                    mainGraphics.drawLine(x, y2, x + width, y);
            }
        }
    }

}
