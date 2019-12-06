package factories;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

import static factories.Storage.SIZE;

class StoragePanel extends JPanel {

    private LinkedList<Baloon> baloons;
    private LinkedList<Baloon> staticBaloons;

    StoragePanel() {
        setBackground(Color.DARK_GRAY);
        baloons = new LinkedList<>();
        staticBaloons = new LinkedList<>();
    }

    void load(LinkedList<Baloon> batch) {
        baloons = batch;
    }

    void addStatic(Baloon baloon) {
        staticBaloons.add(baloon);
    }

    void clearStatic() {
        staticBaloons.clear();
    }

    @Override
    protected void paintComponent(Graphics currentGraphics) {
        super.paintComponent(currentGraphics);
        Graphics2D mainGraphics = (Graphics2D) currentGraphics;

        int frameWidth = getWidth();
        int frameHeight = getHeight();

        mainGraphics.setColor(Color.GRAY);
        int half = frameHeight / 2;
        mainGraphics.drawLine(0, half, frameWidth, half);
        int quarter = frameHeight / 4;
        mainGraphics.drawLine(0, quarter, frameWidth, quarter);

        int width = frameWidth / SIZE;
        int height = frameHeight / SIZE * 2;
        double altitudeUnit = (double)frameHeight / 1000;
        int positionX, altitudeY;
        frameHeight -= height;

        for (Baloon baloon : baloons) {
            mainGraphics.setColor(baloon.getColor());
            positionX = baloon.getPosition() * width;
            altitudeY = frameHeight - (int) (baloon.getAltitude() * altitudeUnit);
            mainGraphics.fillOval(positionX, altitudeY, width, height);
        }

        for (Baloon baloon : staticBaloons) {
            mainGraphics.setColor(baloon.getColor());
            positionX = baloon.getPosition() * width;
            mainGraphics.fillOval(positionX, frameHeight, width, height);
        }
    }

}