package werner.e11.e2;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MyBmiCellRend extends JLabel implements TableCellRenderer {

    MyBmiCellRend() {
        setOpaque(true);
        setHorizontalAlignment(CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((int)value + "");
        if((int)value > 25) setBackground(Color.RED);
        else if ((int) value < 18) setBackground(Color.YELLOW);
        else setBackground(Color.WHITE);
        return this;
    }
}
