package werner.e00.p1;

public class Person {
    private String name;
    private Car car;

    public Person(String name) {
        this.name = name;
    }

    public Person buys(String make, int price) {
        if (car == null) car = new Car(make, price);
        else car = new Car(make, price, car);
        return this;
    }

    public String getName() {
        return name;
    }

    public void showCars() {
        if (car != null) car.showCars();
        else System.out.print("No cars owned");
    }

    public void showCarsRev() {
        if (car != null) car.showCarsRev();
        else System.out.print("No cars owned");
    }

    public int getTotalPrice() {
        int sum = 0;
        Car tmp = car;
        while (tmp != null) {
            sum += tmp.getPrice();
            tmp = tmp.getNext();
        }
        return sum;
    }

    public boolean hasCar(String make) {
        Car tmp = car;
        while (tmp != null) {
            if (tmp.getMake().equalsIgnoreCase(make))
                return true;
            tmp = tmp.getNext();
        }
        return false;
    }

    public Car mostExpensive() {
        Car mostExp = null;
        Car tmp = car;
        while (tmp != null) {
            if (mostExp == null || tmp.getPrice() > mostExp.getPrice()) {
                mostExp = tmp;
            }
            tmp = tmp.getNext();
        }
        return mostExp;
    }
}
