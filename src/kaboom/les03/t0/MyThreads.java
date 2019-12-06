package kaboom.les03.t0;

public class MyThreads {

    private int n;

    public MyThreads(int n) {
        this.n = n;
        new Thread(new PrintErr(this)).start();
        new Thread(new PrintOut(this)).start();
    }

    synchronized public int getN() {
        return n;
    }

    synchronized public void decN() {
        --n;
    }

}
