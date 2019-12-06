package werner.e01.p2;

import java.util.Comparator;

public class MyColorCompar implements Comparator<MyColor> {

    public enum ColComponent {RED, GREEN, BLUE}

    private ColComponent colCom;

    public MyColorCompar(ColComponent colCom) {
        this.colCom = colCom;
    }

    @Override
    public int compare(MyColor mc1, MyColor mc2) {
        switch (colCom) {
            case RED:
                return mc1.getRed() - mc2.getRed();
            case GREEN:
                return mc1.getGreen() - mc2.getGreen();
        }
        return mc1.getBlue() - mc2.getBlue();
    }

}
