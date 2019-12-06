package factories;

import javax.swing.*;
import java.util.Iterator;
import java.util.LinkedList;

import static factories.Storage.SIZE;

public class ReleaseThread extends Thread {

    private static final int HEIGHT = 1000 / SIZE;
    private static final int HALF = 500 - HEIGHT;
    private static final int THREE_QUARTERS = 750 - HEIGHT;

    private final LinkedList<Baloon> baloons;
    private final StoragePanel panel;
    private int sz;

    ReleaseThread(LinkedList<Baloon> baloons, StoragePanel panel) {
        this.baloons = baloons;
        this.panel = panel;
        sz = baloons.size();
    }

    private void altitudeWork() {
        int altitude;
        Iterator<Baloon> iterator = baloons.iterator();
        while (iterator.hasNext()) {
            Baloon baloon =  iterator.next();
            altitude = baloon.getAltitude() + 1;
            if (altitude >= HALF && !baloon.isRed() || altitude >= THREE_QUARTERS) {
                iterator.remove();
                sz--;
            } else {
                baloon.setAltitude(altitude);
                if (altitude < 10) {
                    return;
                }
            }

        }
    }

    public void run() {
        System.out.println("RELEASE START");
        SwingUtilities.invokeLater(panel::repaint);
        while (sz > 0) {
            SwingUtilities.invokeLater(this::altitudeWork);
            SwingUtilities.invokeLater(() -> panel.load(baloons));
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SwingUtilities.invokeLater(panel::repaint);
        }
        SwingUtilities.invokeLater(() -> panel.load(baloons));
        SwingUtilities.invokeLater(panel::repaint);
        System.out.println("RELEASE FINISH");
    }

}
