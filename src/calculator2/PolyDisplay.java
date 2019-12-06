package calculator2;

import javax.swing.*;
import java.awt.*;

class PolyDisplay extends JPanel {

    private MainFrame frame;
    private final int step = 1;
    private final int range = 100;

    PolyDisplay(MainFrame f) {
        frame = f;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth();
        int h = getHeight();

        //System.out.println("w:" + w + " h:" + h + "\n");

        g.setColor(Color.BLUE);
        g.drawLine(w / 2, 0, w / 2, h);
        g.drawLine(0, h / 2, w, h / 2);

        double oneX = (double) w / (range * 2);
        double oneY = (double) h / (range * 2);

        //System.out.println("oneX:" + oneX + " oneY:" + oneY + "\n");

        int drawX1, drawX2, drawY1, drawY2;

        //g.setFont(new Font(getFont().getName(), Font.PLAIN, 10));
        //g.drawString("100", w/2, 0 + getFont().getSize());

        for (Polynomial p : frame.list) {
            if (p.isDisplayed) {
                g.setColor(p.color);
                int x1 = -range, y1 = p.getY(x1);
                int x2, y2;
                drawX1 = (int) ((x1 + range) * oneX);
                drawY1 = (int) ((range - y1) * oneY);
                for (int i = x1 + step; i <= range; i += step) {
                    x2 = i;
                    y2 = p.getY(x2);
                    drawX2 = (int) ((x2 + range) * oneX);
                    drawY2 = (int) ((range - y2) * oneY);

                    if ((y1 <= range && y1 >= -range) || (y2 <= range && y2 >= -range)) {
                        //System.out.println("(" + x1 + "," + y1 + ")->" + "(" + x2 + "," + y2 + ")");
                        //System.out.println("(" + drawX1 + "," + drawY1 + ")->" + "(" + drawX2 + "," + drawY2 + ")\n");
                        g.drawLine(drawX1, drawY1, drawX2, drawY2);
                    }

                    x1 = x2;
                    y1 = y2;
                    drawX1 = drawX2;
                    drawY1 = drawY2;
                }
            }
        }

    }


}
