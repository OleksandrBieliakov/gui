package werner.e00.p1;

public class Main {
    public static void main(String[] args) {
        Person john = new Person("John");
        john.buys("Ford", 20000)
                .buys("Opel", 16000)
                .buys("Fiat", 12000)
                .showCars();
        System.out.println();
        john.showCarsRev();
        System.out.println();
        System.out.println("Total price of " +
                john.getName() + "'s cars: " +
                john.getTotalPrice());
        System.out.println("Does " + john.getName() +
                " have a ford? " + john.hasCar("ford"));
        System.out.println("Does " + john.getName() +
                " have a bmw? " + john.hasCar("bmw"));
        System.out.println(john.getName() + "'s most " +
                "expensive car is " + john.mostExpensive());


        Person bob = new Person("Bob");
        System.out.println();
        bob.showCars();
        System.out.println();
        bob.showCarsRev();
        System.out.println();
        System.out.println("Total price of " +
                bob.getName() + "'s cars: " +
                bob.getTotalPrice());
        System.out.println(bob.getName() + "'s most " +
                "expensive car is " + bob.mostExpensive());
    }
}
