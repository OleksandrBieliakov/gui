package baloons;

import javax.swing.*;
import java.util.Iterator;
import java.util.LinkedList;

import static baloons.Storage.SIZE;

public class ReleaseThread extends Thread {

    private static final int HEIGHT = 1000 / SIZE;
    private static final int HALF = 500 - HEIGHT;
    private static final int THREE_QUARTERS = 750 - HEIGHT;

    private final LinkedList<Balloon> balloons;
    private final Storage panel;

    ReleaseThread(LinkedList<Balloon> balloons, Storage panel) {
        this.balloons = balloons;
        this.panel = panel;
    }

    private void altitudeWork() {
        int altitude;
        Iterator<Balloon> iterator = balloons.iterator();
        while (iterator.hasNext()) {
            Balloon balloon = iterator.next();
            altitude = balloon.getAltitude() + 1;
            if (altitude >= HALF && !balloon.isRed() || altitude >= THREE_QUARTERS) {
                panel.removeBalloon(balloon);
                iterator.remove();
            } else {
                balloon.setAltitude(altitude);
                if (altitude < 10) {
                    break;
                }
            }

        }
        panel.repaint();
    }

    public void run() {
        System.out.println("RELEASE START");
        while (!balloons.isEmpty()) {
            SwingUtilities.invokeLater(this::altitudeWork);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("RELEASE FINISH");
    }

}
