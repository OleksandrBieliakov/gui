package werner.e01.p1;

@FunctionalInterface
interface FunDD {
    double fun(double x);

    static double xminim(FunDD f, double a, double b) {
        double minX = a;
        double minY = f.fun(a);
        double y;
        for (double x = a + 1e-5; x <= b; x += 1e-5) {
            y = f.fun(x);
            if (y < minY) {
                minX = x;
                minY = y;
            }
        }
        return minX;
    }
}
