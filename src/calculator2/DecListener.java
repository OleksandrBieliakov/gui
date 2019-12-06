package calculator2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DecListener implements ActionListener {
    private Calculator cal;

    DecListener(Calculator cal) {
        this.cal = cal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cal.toHex();
    }
}
