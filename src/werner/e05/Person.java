package werner.e05;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private int yearOfBirth;
    private Car car;

    public Person(String n, int y) {
        name = n;
        yearOfBirth = y;
        car = null;
    }

    public Person(String n, int y, Car c) {
        name = n;
        yearOfBirth = y;
        car = c;
    }

    public static List<Car> findAllCars(List<Person> list) {
        List<Car> cars = new ArrayList<>();
        for (Person p : list) {
            if(p.car != null) cars.add(p.car);
        }
        return cars;
    }

    public static List<Person> findOwners(List<Person> list, String carName) {
        List<Person> owners = new ArrayList<>();
        for (Person p : list) {
            if (p.car != null && p.car.getName().equals(carName)) owners.add(p);
        }
        return owners;
    }

    public static Color findColor(List<Person> list, String name, int year) {
        Person p = new Person(name, year);
        if (!list.contains(p)) return null;
        return list.get(list.indexOf(p)).car.getColor();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        Person p = (Person) other;
        return name.equals(p.name) && yearOfBirth == p.yearOfBirth;
    }

    @Override
    public String toString() {
        if (car == null) return name + " " + yearOfBirth;
        return name + " " + yearOfBirth + " " + car.toString();
    }

}
