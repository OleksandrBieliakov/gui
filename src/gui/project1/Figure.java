package gui.project1;

import java.awt.*;

class Figure {

    private static final FigureType[] types = FigureType.values();
    private static final int number_of_types = types.length;

    private FigureType type;
    private Color color;
    private double size;
    private double positionX;
    private double positionY;

    Figure() {
        type = types[(int) (number_of_types * Math.random())];

        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        color = new Color(red, green, blue);

        size = Math.random();
        positionX = Math.random();
        positionY = Math.random();
    }

    FigureType getType() {
        return type;
    }

    Color getColor() {
        return color;
    }

    double getSize() {
        return size;
    }

    double getPositionX() {
        return positionX;
    }

    double getPositionY() {
        return positionY;
    }

}
