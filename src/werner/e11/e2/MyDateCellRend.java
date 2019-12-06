package werner.e11.e2;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyDateCellRend extends JLabel implements TableCellRenderer {

    MyDateCellRend() {
        setOpaque(true);
        setBackground(Color.WHITE);
        setHorizontalAlignment(CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.FRANCE);
        String stringDate = sdf.format((Date)value);
        setText(stringDate);
        return this;
    }
}
