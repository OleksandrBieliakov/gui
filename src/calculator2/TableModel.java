package calculator2;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.zip.DataFormatException;

public class TableModel extends AbstractTableModel {

    private PolyMemory parent;

    TableModel(PolyMemory p) {
        parent = p;
    }

    @Override
    public int getRowCount() {
        return parent.frame.list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Polynomial p = parent.frame.list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.isDisplayed;
            case 1:
                return p.color;
        }
        return p.text;
    }

    @Override
    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int r, int c) {
        return true;
    }

    @Override
    public void setValueAt(Object val, int r, int c) {
        Polynomial p = parent.frame.list.get(r);
        switch (c) {
            case 0:
                p.isDisplayed = (Boolean) val;
                break;
            case 1:
                p.color = (Color) val;
                break;
            case 2:
                try {
                    p.extractElements((String) val);
                } catch (DataFormatException e) {
                    e.printStackTrace();
                }
                break;
        }
        parent.frame.list.set(r, p);
        parent.frame.p1.repaint();
        fireTableCellUpdated(r, c);
    }

}
