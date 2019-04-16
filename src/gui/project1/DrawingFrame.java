package gui.project1;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

class DrawingFrame extends JFrame {

    private File file;

    DrawingFrame(int figuresNeeded, File file) {
        this.file = file;
        add(new GeneratorPanel(this, figuresNeeded));
        setupFrame();
    }

    private void setupFrame() {
        this.setTitle("Drawing panel");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    void saveFigure(Figure figure) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.append(figure.toString());
        } catch (IOException e) {
            e.printStackTrace();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFigures;
    }

}
