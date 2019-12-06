package calculator2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnPolyListener implements ActionListener {

    private Calculator cal;

    OnPolyListener(Calculator cal) {
        this.cal = cal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         cal.onPoly();
    }
}
