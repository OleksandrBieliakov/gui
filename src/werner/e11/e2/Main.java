package werner.e11.e2;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame f = new JFrame("Table");

        JTable table = new JTable(new MyTableModel());

        table.setDefaultRenderer(java.awt.Color.class, new MyColorCellRend());
        table.setDefaultRenderer(java.util.Date.class, new MyDateCellRend());
        table.getColumn("BMI").setCellRenderer(new MyBmiCellRend());

        table.setAutoCreateRowSorter(true);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        f.add(scrollPane);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
