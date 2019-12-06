package factories;

import static factories.BaloonColors.*;

class Main {

    public static void main(String[] args) {

        Storage storage = new Storage();
        new Factory(RED, storage).start();
        new Factory(ORANGE, storage).start();
        new Factory(LIGHT_BLUE, storage).start();

    }

}
