package calculator2;

import javax.swing.*;
import java.awt.*;

class LettersPanel extends JPanel {

    private Calculator cal;

    LettersPanel (Calculator calculator) {
        cal = calculator;
        setLayout(new GridLayout(1, 6));
        add(new NumButton("A", cal));
        add(new NumButton("B", cal));
        add(new NumButton("C", cal));
        add(new NumButton("D", cal));
        add(new NumButton("E", cal));
        add(new NumButton("F", cal));
    }

}
