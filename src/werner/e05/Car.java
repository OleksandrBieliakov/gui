package werner.e05;

import java.awt.*;

public class Car {

    private String name;
    private Color color;

    public Car(String n, int r, int g, int b) {
        name = n;
        color = new Color(r, g, b);
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return name + " " + color.getRed() + " " + color.getGreen() + " " + color.getBlue();
    }

}
