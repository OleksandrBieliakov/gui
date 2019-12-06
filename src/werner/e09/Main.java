package werner.e09;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Main {

    public static void main (String[] args) {

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        MyPanel mp = new MyPanel();
        JSlider sl = new JSlider(JSlider.HORIZONTAL,0, 100, 50);
        sl.setMajorTickSpacing(20);
        sl.setMinorTickSpacing(10);
        sl.setPaintLabels(true);
        sl.setPaintTicks(true);
        sl.setBorder(new TitledBorder("Size of the square"));
        sl.addChangeListener(e -> {
            JSlider s = (JSlider)e.getSource();
            System.out.println(s.getValue());
            mp.setSize((double) s.getValue()/100);
            mp.repaint();
        });

        p.add(mp);
        p.add(sl);

        JFrame f = new JFrame("Resizable square");
        f.add(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600,400);
        f.setLocationRelativeTo(null);
        f.setVisible(true);


    }
}
