package werner.e04.p1;

import java.util.Collection;

public class Person implements Comparable<Person> {
    private String name;
    private int yearOfBirth;

    public Person(String n, int y) {
        name = n;
        yearOfBirth = y;
    }

    @Override
    public String toString() {
        return name + "(" + yearOfBirth + ")";
    }

    public static boolean findInColl(Collection<Person> coll, String name, int year) {
        return coll.contains(new Person(name, year));
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        Person p = (Person) other;
        return name.equals(p.name) && yearOfBirth == p.yearOfBirth;
    }

    @Override
    public int hashCode() {
        return 17 * name.hashCode() + yearOfBirth;
    }

    @Override
    public int compareTo(Person other) {
        int diff = yearOfBirth - other.yearOfBirth;
        if (diff != 0) return diff;
        return name.compareTo(other.name);
    }
}
