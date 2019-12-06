package kaboom.les03.t2;

public class Main {

    public static void main(String[] args) {
        int x = 0;
        for (int i = 0; i < 1000; i++) {
            x += i;
        }
        System.out.println("x = " + x);
        System.out.println("3x = " + (3*x));

        Counter c = new Counter();
        AddingThread at0 = new AddingThread(c);
        at0.start();
        AddingThread at1 = new AddingThread(c);
        at1.start();
        AddingThread at2 = new AddingThread(c);
        at2.start();
        System.out.println("Counter.x = " + c);
    }

}
