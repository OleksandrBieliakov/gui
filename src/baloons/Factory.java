package baloons;

import java.util.Random;

class Factory extends Thread {

    private BalloonColors color;
    private final Storage storage;

    Factory(BalloonColors color, Storage storage) {
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
                storage.addBalloon(new Balloon(color));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
