package calculator2;

import java.util.TreeSet;

class OperButton extends CalButton {

    final TreeSet<Character> chars = new TreeSet<>();

    OperButton(String title, Calculator cal) {
        super(title, cal);
        addChars();
    }

    void addChars() {
        chars.add('+');
        chars.add('*');
        chars.add('/');
        chars.add('^');
        chars.add('.');
    }

    @Override
    void addListener() {
        addActionListener(e -> {
            char[] curr = cal.getOutput().toCharArray();
            int i = curr.length - 1;
            if (curr[i] == '\u221A' || curr[i] == '(' || curr[i] == '-') return;
            if (chars.contains(curr[i])) {
                curr[i] = title.charAt(0);
                cal.setOutput(new String(curr));
            } else {
                cal.setOutput(cal.getOutput() + title);
            }
        });
    }

}
