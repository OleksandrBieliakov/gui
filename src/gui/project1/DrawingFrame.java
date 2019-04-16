package gui.project1;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

class DrawingFrame extends JFrame {

    private File file;

    DrawingFrame(int figuresNeeded, File file) {
        this.file = file;
        add(new GeneratorPanel(this, figuresNeeded));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        this.setLocation(0, 0);
        this.setSize(width / 2, width / 2);

        this.setTitle("Painting module");
        setupFrame();
    }

    DrawingFrame(File file) {
        this.file = file;
        add(new DisplayPanel(this));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        this.setLocation(width / 2, 0);
        this.setSize(width / 2, width / 2);

        this.setTitle("Display module");
        setupFrame();
    }

    private void setupFrame() {
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    void saveFigure(Figure figure) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.append(figure.toString());
        } catch (IOException ignored) {
        }
    }

    ArrayList<Figure> readFile(int skipLines) {
        ArrayList<Figure> newFigures = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (skipLines-- > 0) {
                br.readLine();
            }
            String s;
            FigureType type;
            int red;
            int green;
            int blue;
            double size;
            double positionX;
            double positionY;
            while ((s = br.readLine()) != null) {
                type = FigureType.valueOf(s);
                red = Integer.parseInt(br.readLine());
                green = Integer.parseInt(br.readLine());
                blue = Integer.parseInt(br.readLine());
                size = Double.parseDouble(br.readLine());
                positionX = Double.parseDouble(br.readLine());
                positionY = Double.parseDouble(br.readLine());
                newFigures.add(new Figure(type, red, green, blue, size, positionX, positionY));
            }
        } catch (IOException ignored) {
        }
        return newFigures;
    }

}
