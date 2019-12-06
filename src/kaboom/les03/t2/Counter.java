package kaboom.les03.t2;

public class Counter {

    private int x = 0;
    void inc(int value) {
        int sum = x + value;
        x = sum;
    }
    public String toString() {
        return x + "";
    }


}
