package werner.e01.p1;

public class Main {

    public static void main(String[] args) {

        System.out.println(FunDD.xminim(new Parabola(1, -1, 5 / 4), 0, 1));

        System.out.println(FunDD.xminim(new FunDD() {
            @Override
            public double fun(double x) {
                return Math.sqrt((x - 0.75) * (x - 0.75) + 1);
            }
        }, 0, 2));

        System.out.println(FunDD.xminim(i -> i * i * (i - 2), 0, 2));

    }
}
