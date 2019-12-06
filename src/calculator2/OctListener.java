package calculator2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OctListener implements ActionListener {

    Calculator cal;

    OctListener(Calculator calculator) {
        cal = calculator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cal.toBin();
    }
}
