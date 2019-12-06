package calculator2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.zip.DataFormatException;

class PolyMemory extends JPanel {

    MainFrame frame;
    private JButton addB = new JButton("ADD");
    private JButton removeB = new JButton("REMOVE");
    private JPanel buttonsP = new JPanel();
    private JTable table = new JTable(new TableModel(this));

    private void setButtons() {
        addB.setBackground(Color.DARK_GRAY);
        addB.setForeground(Color.WHITE);
        removeB.setBackground(Color.DARK_GRAY);
        removeB.setForeground(Color.WHITE);
        addB.setFont(new Font(getFont().getName(), Font.BOLD, 15));
        removeB.setFont(new Font(getFont().getName(), Font.BOLD, 15));

        buttonsP.add(addB);
        buttonsP.add(removeB);
    }


    PolyMemory(MainFrame f) {
        frame = f;

        setLayout(new BorderLayout());
        setButtons();

        JLabel title = new JLabel("POLYNOMIALS");
        title.setFont(new Font(getFont().getName(), Font.BOLD, 12));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(title, BorderLayout.NORTH);

        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);

        JScrollPane scr = new JScrollPane(table);
        add(scr, BorderLayout.CENTER);
        add(buttonsP, BorderLayout.SOUTH);
        table.setBackground(Color.LIGHT_GRAY);
        table.setDefaultRenderer(java.awt.Color.class, new MyColorCellRend());
        table.getColumnModel().getColumn(1).setCellEditor(new ColorEditor());
        table.setTableHeader(null);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(1).setMaxWidth(10);
        table.setFont(new Font(getFont().getName(), Font.PLAIN, 15));
        table.setRowSelectionAllowed(false);

        addB.addActionListener(e -> {
            String s = JOptionPane.showInputDialog("Enter a polynomial (e.g. 2.5x^2-x+3)");
            if (s != null) {
                try {
                    frame.list.add(new Polynomial(s));
                } catch (DataFormatException ex) {
                    ex.printStackTrace();
                }
                table.updateUI();
                frame.p1.repaint();
            }
        });

        removeB.addActionListener(e -> {
            int i = table.getSelectedRow();
            if(i != -1) {
                frame.list.remove(i);
                table.updateUI();
                frame.p1.repaint();
            }
        });

    }


}
