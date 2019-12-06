package werner.e04.p2;

public class Main {
    public static void main(String... args) {
        int ini = 10, count = -1, maxel = 0;
        Hailstone hailstone = new Hailstone(ini);
        for (int h : hailstone) {
            if (h > maxel) maxel = h;
            ++count;
        }
        System.out.println("\n" + ini + " " + count + " " + maxel);
    }
}
