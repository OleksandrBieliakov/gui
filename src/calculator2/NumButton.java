package calculator2;

class NumButton extends CalButton {
    NumButton(String title, Calculator cal) {
        super(title, cal);
    }

    @Override
    void addListener() {
        addActionListener(e -> {
            if (cal.getOutput().equals("0")) {
                cal.setOutput(title);
            } else {
                cal.setOutput(cal.getOutput() + title);
            }
        });
    }
}
