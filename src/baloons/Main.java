package baloons;

import javax.swing.*;
import java.awt.*;

import static baloons.BalloonColors.*;

class Main {

    static void createAndShowGui(Container panel) {
        JFrame frame = new JFrame("Flying balloons");
        frame.setContentPane(panel);
        panel.setBackground(Color.DARK_GRAY);
        int size = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.95);
        frame.setSize(size, size);
        frame.setSize(size, size);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Storage storage = new Storage();
            createAndShowGui(storage);
            new Factory(RED, storage).start();
            new Factory(ORANGE, storage).start();
            new Factory(LIGHT_BLUE, storage).start();
        });
    }

}
