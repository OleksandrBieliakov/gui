package werner.e11.e1;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static JFrame f = new JFrame("LIST");
    private static MyListModel m = new MyListModel(f);
    private static JList<String> list = new JList<>(m);


    private static void operateCommand(String s) {
        String[] arr = s.split(" ");
        if (arr[0].equalsIgnoreCase("add")) {
            m.addEl(arr[1]);
        } else if (arr[0].equalsIgnoreCase("del")) {
            m.delEl(arr[1]);
        } else if (arr[0].equalsIgnoreCase("quit")) {
            System.exit(1);
        } else JOptionPane.showMessageDialog(f,
                "Wrong command",
                "Warning",
                JOptionPane.WARNING_MESSAGE);

    }

    public static void main(String[] args) {

        list.setForeground(Color.RED);
        JScrollPane scr = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scr.setPreferredSize(new Dimension(200, 250));
        f.add(scr, BorderLayout.CENTER);

        JTextField tf = new JTextField("ENTER COMMAND");
        tf.setForeground(Color.BLUE);

        tf.addActionListener(e -> {
            operateCommand(e.getActionCommand());
        });

        f.add(tf, BorderLayout.SOUTH);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }
}
