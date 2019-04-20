package gui.project1;

import javax.swing.*;
import java.awt.*;
import java.io.*;

class DrawingFrame extends JFrame {

    private File file;
    private int figuresInList;
    private DrawingPanel panel;

    DrawingFrame(File file, String title, FramePositioning positioning) {
        this.file = file;
        panel = new DrawingPanel();
        this.setTitle(title);

        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.95);

        switch (positioning) {
            case ONE_CENTER:
                this.setSize(height, height);
                this.setLocationRelativeTo(null);
                break;
            case TWO_LEFT:
                this.setSize(width / 2, height);
                this.setLocation(0, 0);
                break;
            case TWO_RIGHT:
                this.setSize(width / 2, height);
                this.setLocation(width / 2, 0);
                break;
            case THREE_LEFT:
                this.setSize(width / 3, height);
                this.setLocation(0, 0);
                break;
            case THREE_CENTER:
                this.setSize(width / 3, height);
                this.setLocation(width / 3, 0);
                break;
            case THREE_RIGHT:
                this.setSize(width / 3, height);
                this.setLocation(width / 3 * 2, 0);
                break;
        }

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SwingUtilities.invokeLater(this::readFile);
        add(panel);
        SwingUtilities.invokeLater(this::repaint);
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
