package gui.project1;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

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

        String s = JOptionPane.showInputDialog(null, "Enter a number of figures you want to generate");

        new DrawingFrame(Integer.parseInt(s), file);

        new DrawingFrame(file);

    }

}
