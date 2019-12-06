package calculator2;

import javax.swing.*;
import java.awt.*;

class CalButton extends JButton {

    Calculator cal;
    String title;

    CalButton(String title, Calculator cal) {
        setText(title);
        this.title = title;
        this.cal = cal;
        setBackground(Color.DARK_GRAY);
        setForeground(Color.WHITE);
        addListener();
        setFont(new Font(getFont().getName(), Font.BOLD, 15));
    }

    void addListener() {
        addActionListener(e -> {
            cal.setOutput(cal.getOutput() + title);
        });
    }

}
