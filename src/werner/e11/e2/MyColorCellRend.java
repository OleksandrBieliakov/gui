package werner.e11.e2;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MyColorCellRend extends JLabel implements TableCellRenderer {

    MyColorCellRend() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setBackground((Color)value);
        return this;
    }
}
