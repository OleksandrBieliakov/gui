package werner.e08;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static void setFrame(JFrame f) {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private static void addButtons(JPanel p, int from, int to, String text) {
        while(from <= to ) {
            if(from <= 9) {
                p.add(new JButton(text + "0" + from));
            }
            else {
                p.add(new JButton(text + from));
            }
            from++;
        }
    }

    private static JPanel threeTFields() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        for(int i = 1; i < 4; ++i) {
            p.add(new JTextField("JTextField " + i, 10));
        }
        return p;
    }


    public static void main (String[] args) {

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(threeTFields());
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        p1.add(new JTextArea("JTextArea 1", 5, 10));
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(3, 3));
        addButtons(p2, 1, 9, "B");
        p1.add(p2);
        p.add(p1);
        JFrame f = new JFrame("First one");
        f.add(p);
        setFrame(f);




        JPanel sec = new JPanel();
        sec.setLayout(new BoxLayout(sec, BoxLayout.Y_AXIS));
        sec.add(new JTextArea("JTextArea 1", 3, 20));

        JPanel sec1 = new JPanel();
        sec1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JPanel sec1grid1 = new JPanel();
        sec1grid1.setLayout(new GridLayout(2, 2,3,3));
        addButtons(sec1grid1, 1, 4, "B");
        sec1.add(sec1grid1);

        sec1.add(threeTFields());

        JPanel sec1grid2 = new JPanel();
        sec1grid2.setLayout(new GridLayout(2, 2,3,3));
        addButtons(sec1grid2, 5, 8, "B");
        sec1.add(sec1grid2);

        sec.add(sec1);
        JFrame f2 = new JFrame("Second one");
        f2.add(sec);
        setFrame(f2);




        JPanel thr = new JPanel();
        thr.setLayout(new BoxLayout(thr, BoxLayout.Y_AXIS));
        thr.add(threeTFields());

        JPanel thr1 = new JPanel();
        thr1.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 0));
        thr1.add(new JTextArea("JTextArea 1", 3, 12));
        thr1.add(new JTextArea("JTextArea 2", 3, 12));
        thr.add(thr1);

        JPanel thr2 = new JPanel();
        thr2.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 0));
        addButtons(thr2, 1 , 5, "");
        thr.add(thr2);

        JFrame f3 = new JFrame("Third one");
        f3.add(thr);
        setFrame(f3);




        JPanel four = new JPanel();
        four.setLayout(new BoxLayout(four, BoxLayout.Y_AXIS));

        JPanel four1 = new JPanel();
        four1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        four1.add(new JTextArea("JTextArea 1", 5, 6));
        JPanel fourGrid = new JPanel(new GridLayout(3, 4, 2, 2));
        addButtons(fourGrid, 1, 12, "");
        four1.add(fourGrid);
        four1.add(new JTextArea("JTextArea 2", 5, 6));
        four.add(four1);
        four.add(new JTextField("JTextField 4"));

        JFrame f4 = new JFrame("Fourth one");
        f4.add(four);
        setFrame(f4);




        JPanel fifth = new JPanel();
        fifth.setLayout(new BoxLayout(fifth, BoxLayout.Y_AXIS));

        JPanel fifthGrid = new JPanel(new GridLayout(2,5,2,2));
        addButtons(fifthGrid, 1, 10, "B");
        fifth.add(fifthGrid);

        fifth.add(threeTFields());

        JPanel fifth1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 4,4));
        addButtons(fifth1, 11,13, "B");
        fifth.add(fifth1);

        JFrame f5 = new JFrame("Fifth one");
        f5.add(fifth);
        setFrame(f5);




        JPanel six = new JPanel();
        six.setLayout(new BoxLayout(six, BoxLayout.Y_AXIS));

        JPanel sixGrid = new JPanel(new GridLayout(1, 4, 2,2));
        addButtons(sixGrid, 1, 4 , "");
        six.add(sixGrid);

        JPanel six1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 1 ,1));
        six1.add(new JTextArea("JTextArea 1", 4, 12));
        six1.add(threeTFields());
        six.add(six1);

        JPanel sixGrid2 = new JPanel(new GridLayout(1, 4, 2,2));
        addButtons(sixGrid2, 5, 8 , "");
        six.add(sixGrid2);

        JFrame f6 = new JFrame("Sixth one");
        f6.add(six);
        setFrame(f6);




    }

}
