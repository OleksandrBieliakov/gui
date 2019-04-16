package gui.project1;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        File dir = new File("data");

        if (!dir.isDirectory()) {
            boolean created = dir.mkdir();
            if (!created) {
                System.err.println("Cannot create data dir");
                System.exit(1);
            }
        }

        File file = new File(dir, "figures");

        new DrawingFrame(50, file);

        new DrawingFrame(file);

    }

}
