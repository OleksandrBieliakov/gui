package kaboom.les03.t1;

public class Main {
    public static void main (String[] args) {
        long start = System.currentTimeMillis();
        long finish;
        for(int i = 0; i <= 2_000_000_000; ++i) {}
            //System.out.print(i + " ");
        System.out.println("\nLoop: " + (System.currentTimeMillis()-start));

        new Thread(() -> {
            long start1 = System.currentTimeMillis();
            for(int i = 0; i <= 2_000_000_000; i += 2) {}
                //System.err.print(i + " ");
            System.out.println("\nEvens: " + (System.currentTimeMillis()-start1));
        }).start();

        new Thread(() -> {
            long start2 = System.currentTimeMillis();
            for(int i = 1; i < 2_000_000_000; i += 2) {}
                //System.out.print(i + " ");
            System.out.println("\nOdds: " + (System.currentTimeMillis()-start2));
        }).start();

    }
}
