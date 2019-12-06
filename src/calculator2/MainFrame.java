package calculator2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    ArrayList<Polynomial> list = new ArrayList<>();
    PolyDisplay p1 = new PolyDisplay(this);
    private PolyMemory p2 = new PolyMemory(this);
    private Calculator cal = new Calculator(this);

    MainFrame(String s) {
        super(s);
        add(cal, BorderLayout.CENTER);

        p1.setPreferredSize(new Dimension(100, 400));
        p1.setBackground(Color.LIGHT_GRAY);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        add(p1, BorderLayout.SOUTH);
        add(p2, BorderLayout.EAST);
        p1.setVisible(false);
        p2.setVisible(false);
    }

    void displayPoly() {
        p1.setVisible(true);
        p2.setVisible(true);
        pack();
    }

    void hidePoly() {
        p1.setVisible(false);
        p2.setVisible(false);
        pack();
    }

    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }

        MainFrame f = new MainFrame("Calculator");



    }
}
