package werner.e05;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Person> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("data/gui/e05"))) {

            String s;
            String[] arr;
            while ((s = br.readLine()) != null) {
                arr = s.split("\\s+");
                if (arr.length == 2) {
                    list.add(new Person(arr[0], Integer.parseInt(arr[1])));
                } else {
                    list.add(new Person(arr[0], Integer.parseInt(arr[1]), new Car(arr[2], Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5]))));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(list);
        System.out.println(Person.findAllCars(list));
        System.out.println(Person.findOwners(list, "Skoda"));
        System.out.println(Person.findColor(list, "Alice", 1993));

    }

}
