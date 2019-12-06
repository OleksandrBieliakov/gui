package kaboom.les03.t0;

public class PrintErr implements Runnable {

    private MyThreads thr;

    public PrintErr(MyThreads thr) {
        this.thr = thr;
    }

    @Override
    public void run() {
        while (thr.getN() > 0) {
            System.err.print("|" + thr.getN() + "|");
            thr.decN();
            for (int i = 0; i < 10; ++i) {
                System.err.print(i + " ");
            }
        }
    }

}
