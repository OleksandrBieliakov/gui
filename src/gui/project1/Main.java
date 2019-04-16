package gui.project1;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        File file = new File("data/figures");

        new DrawingFrame(5, file);

    }

}
