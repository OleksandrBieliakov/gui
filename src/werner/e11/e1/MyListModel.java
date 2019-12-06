package werner.e11.e1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MyListModel extends AbstractListModel<String> {

    private JFrame parent;
    private List<String> l = new ArrayList<>();

    MyListModel(JFrame parent) {
        this.parent = parent;
        l.add("Kasia");
        l.add("Саня");
        l.add("Bob");
    }

    public void addEl(String el) {
        if (l.contains(el)) {
            JOptionPane.showMessageDialog(parent,
                    "\"" + el + "\" already in the list",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        l.add(el);
        fireIntervalAdded(this, l.size() - 1, l.size() - 1);
    }

    public void delEl(String el) {
        if (!l.contains(el)) {
            JOptionPane.showMessageDialog(parent,
                    "\"" + el + "\" is not in the list",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int ind = l.indexOf(el);
        l.remove(el);
        fireIntervalRemoved(this, ind, ind);
    }

    @Override
    public int getSize() {
        return l.size();
    }

    @Override
    public String getElementAt(int index) {
        return l.get(index);
    }
}
