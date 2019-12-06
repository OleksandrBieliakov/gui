package werner.e04.p1;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        List<Person> list = new ArrayList<>();

        String name;
        String year;

        System.out.println("Enter \"end\" after Name: to finish input.");

        while (true) {
            System.out.print("Name: ");
            name = scan.nextLine();
            if (name.equals("end")) break;
            try {
                System.out.print("Year: ");
                year = scan.nextLine();
                list.add(new Person(name, Integer.parseInt(year)));
            } catch (NumberFormatException e) {
                System.out.println("Year of birth must be an integer.\n" +
                        "Please enter name and year again.");
                continue;
            }
            System.out.println();
        }

        System.out.println(list);
        System.out.println(Person.findInColl(list, "Joe", 33));

        Set<Person> hash = new HashSet<>(list);
        System.out.println(Person.findInColl(hash, "Joe", 33));

        Set<Person> tree = new TreeSet<>(list);
        System.out.println(Person.findInColl(tree, "Joe", 33));

    }

}
