package factories;

import java.util.Random;

class Factory extends Thread {

    private BaloonColors color;
    private final Storage storage;

    Factory(BaloonColors color, Storage storage) {
        this.color = color;
        this.storage = storage;
    }

    @Override
    public void run() {
        Random random = new Random();
        //noinspection EndlessStream
        random.ints(600, 1600).forEach(waitTime -> {
            try {
                Thread.sleep(waitTime);
                storage.addBaloon(new Baloon(color));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
