package werner.e04.p2;

import java.util.Iterator;

public class Hailstone implements Iterable<Integer> {
    private int a;
    private boolean isNext = true;

    public Hailstone(int a) {
        this.a = a;
    }

    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return isNext;
            }

            @Override
            public Integer next() {
                System.out.print(a + " ");
                if (a == 1)
                    isNext = false;
                else
                    a = a % 2 == 0 ? a / 2 : a * 3 + 1;
                return a;
            }
        };
    }

}
