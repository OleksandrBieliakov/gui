package baloons;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class Storage extends JPanel {

    static final int SIZE = 25;
    //0 LEAPS means endless repeating, 1 is useful for testing
    private static final int LEAPS = 0;

    private Set<Balloon> balloons;
    private LinkedList<Balloon> staticBalloons;

    private int leap = 0;

    Storage() {
        balloons = new HashSet<>();
        staticBalloons = new LinkedList<>();
    }

    void addBalloon(Balloon balloon) {
        int size = staticBalloons.size();
        if (size == SIZE) {
            System.out.println("RELEASE CALL");
            new ReleaseThread(staticBalloons, this).start();
            staticBalloons = new LinkedList<>();
            leap++;
        } else if (LEAPS == 0 || leap < LEAPS) {
            balloon.setPosition(size);
            staticBalloons.add(balloon);
            SwingUtilities.invokeLater(() -> {
                balloons.add(balloon);
                repaint();
            });
            System.out.println("(" + size + ") " + balloon);
        }
    }

    void removeBalloon(Balloon balloon) {
        balloons.remove(balloon);
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

        for (Balloon balloon : balloons) {
            mainGraphics.setColor(balloon.getColor());
            positionX = balloon.getPosition() * width;
            altitudeY = frameHeight - (int) (balloon.getAltitude() * altitudeUnit);
            mainGraphics.fillOval(positionX, altitudeY, width, height);
        }
    }

}
