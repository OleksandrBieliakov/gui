package gui.project1v2;

import javax.swing.*;
import java.awt.*;
import java.io.*;

class DrawingFrame extends JFrame {

    private File file;
    private int figuresInList;
    private DrawingPanel panel;

    DrawingFrame(File file, boolean type) { // type: true - painting module, false - display module
        this.file = file;
        panel = new DrawingPanel();
        int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
        this.setSize(width, width);
        if (type) {
            this.setLocation(0, 0);
            this.setTitle("Painting module");
        } else {
            this.setLocation(width, 0);
            this.setTitle("Display module");
        }
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        readFile();
        add(panel);
    }

    void refresh() {
        panel.repaint();
    }

    void generateFigure() {
        Figure newFigure = new Figure();
        panel.addFigureToList(newFigure);
        figuresInList++;
        saveFigure(newFigure);
    }

    private void saveFigure(Figure figure) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.append(figure.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int skipLines = figuresInList * 7;
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
                panel.addFigureToList(new Figure(type, red, green, blue, size, positionX, positionY));
                figuresInList++;
            }
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
