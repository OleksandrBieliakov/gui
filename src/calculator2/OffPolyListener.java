package calculator2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OffPolyListener implements ActionListener {
    private Calculator cal;

    OffPolyListener(Calculator cal) {
        this.cal = cal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cal.offPoly();
    }
}
