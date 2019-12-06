package werner.e06;

import java.util.Iterator;

class Letters implements Iterable<Thread>{

    private Thread[] threads;

    Letters (String s) {
        int len = s.length();
        threads = new Thread[len];
        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);
            threads[i] = new Thread(() -> {
                    while(true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            return;
                        }
                        System.out.print(c);
                    }
                }, "Thread " + c);
        }
    }

    void start() {
        for(Thread th : threads) {
            th.start();
        }
    }

    void stop() {
        for(Thread th : threads) {
            th.interrupt();
        }
    }

    public Iterator<Thread> iterator() {
        return new LetterIterator<>(threads);
    }

    class LetterIterator<Thread> implements Iterator<Thread> {
        private final Thread[] a;
        private final int len;
        int current = 0;
        LetterIterator (Thread[] a) {
            this.a = a;
            len = a.length;
        }
        public boolean hasNext() {
            return current < len;
        }
        public Thread next() {
            return a[current++];
        }
    }

}
