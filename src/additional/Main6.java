package additional;

public class Main6 {

    public static void main (String[] args) {

        Book b1 = new Book();
        Book b2 = new Book("LOTR", 1000, "A. Tolk");
        System.out.println(b1);
        System.out.println(b2);

        Manual m1 = new Manual();
        System.out.println(m1 + "\n");

        Book k1 = new Book("Title_1", 45, "Anonymous");
        try {
            Book k2 = new Manual("Tytul_2", 34, "Anna Nowak", "Printer");
            Book k3 = new Manual("Tytul_3", 23, "Jan Kowalski", "DVD");
            Book[] arr = {k1, k2, k3};
            for (Book k : arr)
                System.out.println(k);

        } catch (WrongDeviceException e) {
            System.out.print(e);
        }

    }

}
