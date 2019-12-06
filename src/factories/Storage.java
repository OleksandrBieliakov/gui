package factories;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

class Storage extends JFrame {

    static final int SIZE = 99;
    //0 LEAPS means endless repeating, 1 is useful for testing
    private static final int LEAPS = 0;

    private LinkedList<Baloon> baloons;
    private StoragePanel panel;
    private int size = 0;
    private int leap = 0;

    Storage() {
        baloons = new LinkedList<>();
        panel = new StoragePanel();
        int size = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.95);
        setSize(size, size);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(panel);
    }

    void addBaloon(Baloon baloon) {
        if (size == SIZE) {
            System.out.println("RELEASE CALL");
            new ReleaseThread(new LinkedList<>(baloons), panel).start();
            SwingUtilities.invokeLater(panel::clearStatic);
            baloons.clear();
            size = 0;
            leap++;
        }
        if (LEAPS == 0 || leap < LEAPS) {
            baloon.setPosition(size++);
            baloons.add(baloon);
            SwingUtilities.invokeLater(() -> panel.addStatic(baloon));
            SwingUtilities.invokeLater(panel::repaint);
            System.out.println("(" + size + ")col: " + baloon.getColor() + ", a: " + baloon.getAltitude() + ", p: " + baloon.getPosition());
        }
    }

}
