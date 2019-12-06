package werner.e00.p1;

public class Car {
    private String make;
    private int price;
    private Car next;

    public Car(String m, int p, Car n) {
        make = m;
        price = p;
        next = n;
    }

    public Car(String make, int price) {
        this.make = make;
        this.price = price;
        next = null;
    }

    public String getMake() {
        return make;
    }

    public int getPrice() {
        return price;
    }

    public Car getNext() {
        return next;
    }

    public void showCars() {
        System.out.print(toString());
        if (next != null){
            System.out.print(' ');
            next.showCars();
        }
    }

    public void showCarsRev() {
        if (next != null) {
            next.showCarsRev();
            System.out.print(' ');
        }
        System.out.print(toString());
    }

    @Override
    public String toString() {
        return make + "(" + price + ")";
    }
}