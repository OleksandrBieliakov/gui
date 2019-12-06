package werner.e01.p2;

public class MyColor extends java.awt.Color implements Comparable<MyColor> {

    public MyColor(int r, int g, int b) {
        super(r, g, b);
    }

    private int getSum() {
        return super.getRed() + super.getGreen() + super.getBlue();
    }

    @Override
    public int compareTo(MyColor mc) {
        return getSum() - mc.getSum();
    }

    @Override
    public String toString() {
        return "(" + super.getRed() + "," + super.getGreen() + "," + super.getBlue() + ")";
    }

}
