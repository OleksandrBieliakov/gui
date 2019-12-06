package calculator2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BinListener implements ActionListener {
    private Calculator cal;

    BinListener(Calculator cal) {
        this.cal = cal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cal.toDec();
    }
}
