package kaboom.les03.t1;

public class Main2 {

    public static void main(String[] args) {
        int numOfThreads = 10;
        for (int i = 0; i < numOfThreads; i++) {
            createThread(String.valueOf(i + 1), i, numOfThreads);
        }
        createThread("A", 0, 1);
    }

    private static void createThread(String title, int from, int step) {
        new Thread(() -> {
            long start = System.currentTimeMillis();
            long c = 0;
            double x = 0.0;
            boolean toggle = false;
            for (int i = from; i < 1_000_000 + from; i += step) {
                c++;
                toggle = !toggle;
                if (toggle)
                    x += doHardWork();
                else
                    x -= doHardWork();
            }
            System.out.println(title + " : " + c + " : " + x + " : " + (System.currentTimeMillis() - start));
        }).start();
    }

    private static double doHardWork() {
        double dummy = 0.0;
        for (int i = 0; i < 1000; i++) {
            dummy += i;
        }
        return dummy;
    }

}
