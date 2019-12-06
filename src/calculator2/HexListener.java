package calculator2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HexListener implements ActionListener {
    private Calculator cal;

    HexListener(Calculator cal) {
        this.cal = cal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cal.toOct();
    }
}
