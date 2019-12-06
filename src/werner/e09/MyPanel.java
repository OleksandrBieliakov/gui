package werner.e09;

import javax.swing.*;
import java.awt.*;

class MyPanel extends JPanel {

    double size;

    MyPanel() {
        size = 0.5;
        setBackground(Color.lightGray);
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth();
        int h = getHeight();

        int edge = (int)((w < h ? w : h) *size);

        int x = (w - edge)/2;
        int y = (h - edge)/2;

        g.setColor(Color.BLUE);
        g.fillRect(x, y, edge, edge);

    }

}
