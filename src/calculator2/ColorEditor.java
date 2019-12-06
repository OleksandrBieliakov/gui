package calculator2;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class ColorEditor extends AbstractCellEditor implements TableCellEditor {

    JPanel panel = new JPanel();
    Color color;

    ColorEditor() {
        panel.setOpaque(true);
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Color newColor = JColorChooser.showDialog(null, "Choose polynomial color", Color.BLACK);
                if (newColor != null) {
                    color = newColor;
                    panel.setBackground(color);
                    fireEditingStopped();
                }
            }
        });

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        panel.setBackground((Color) value);
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return color;
    }
}
