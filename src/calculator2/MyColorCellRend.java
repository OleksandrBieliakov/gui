package calculator2;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class MyColorCellRend extends JLabel implements TableCellRenderer {
    MyColorCellRend() {
        setOpaque(true);
    }

    public Component
    getTableCellRendererComponent(
            JTable table, Object color, boolean isSelected,
            boolean hasFocus, int row, int column) {
        setBackground((Color) color);
        return this;
    }
}