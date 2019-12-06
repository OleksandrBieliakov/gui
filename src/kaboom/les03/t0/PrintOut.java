package kaboom.les03.t0;

public class PrintOut implements Runnable {

    private MyThreads thr;

    public PrintOut(MyThreads thr) {
        this.thr = thr;
    }

    @Override
    public void run() {
        while (thr.getN() > 0) {
            System.out.print("|" + thr.getN() + "|");
            thr.decN();
            for (int i = 0; i < 10; ++i) {
                System.out.print(i + " ");
            }
        }
    }

}
