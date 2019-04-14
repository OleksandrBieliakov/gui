package gui.project1;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {

    private DrawingFrame drawingFrame;

    DrawingPanel(DrawingFrame drawingFrame) {
        this.drawingFrame = drawingFrame;
        setupPanel();
    }

    private void setupPanel() {
        this.setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics currentGraphics) {
        super.paintComponent(currentGraphics);
        Graphics2D mainGraphics = (Graphics2D) currentGraphics;

        int frameWidth = getWidth();
        int frameHeight = getHeight();
        double size;
        int x, y, width, height, x2, y2;

        for (Figure figure : drawingFrame.getFigures()) {
            mainGraphics.setStroke(new BasicStroke(5));
            mainGraphics.setColor(figure.getColor());
            size = figure.getSize();
            width = (int) (frameWidth / 16 + (size * frameWidth / 8));
            height = (int) (frameHeight / 16 + (size * frameHeight / 8));
            x = (int) ((frameWidth - width) * figure.getPositionX());
            y = (int) ((frameHeight - height) * figure.getPositionY());

            switch (figure.getType()) {
                case RECTANGLE:
                    mainGraphics.setStroke(new BasicStroke(15));
                    mainGraphics.drawRect(x, y, width, height);
                    break;
                case ELLIPSE:
                    mainGraphics.setStroke(new BasicStroke(15));
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
                case DEATHLY_HALLOWS:
                    // TRIANGLE_TOP
                    mainGraphics.drawLine(x + width / 2, y, x2 = x + width, y2 = y + height);
                    mainGraphics.drawLine(x2, y2, x, y2);
                    mainGraphics.drawLine(x, y2, x + width / 2, y);

                    // LINE
                    mainGraphics.drawLine(x2 = x + width/2, y, x2, y + height);

                    // CIRCLE
                    int side = (int)(Math.sqrt(height*height + (width/2)*(width/2)));
                    int diameterY = 2*(int)(side*Math.sqrt(3)/6);
                    int diameterX = diameterY*width/height;
                    x = x + width/2 - diameterX/2;
                    y = y + height - diameterY;
                    mainGraphics.drawOval(x, y, diameterX, diameterY);
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

        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        repaint();
    }

}
