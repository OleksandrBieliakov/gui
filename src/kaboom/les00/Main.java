package kaboom.les00;

public class Main {
    public static void main(String args[]) {

        Person p1 = new Person("Jan", 50); //name, age
        Student s1 = new Student("Jasiek", 20, 2); //name, age, semester
        Person p3 = (Person)s1;
        Student s2 = new Student("Anna", 21, 4);

        p1.sayHelloTo(p3); // Hi Jasiek!
        p3.sayHelloTo(p1); // Hi Jan!
        s1.sayHelloTo(s2); // Yo, Anna!
        s1.sayHelloTo(p1); // Good morning, Jan!

        Subject s = new Subject("GUI");
        s.setTeacher(p1);

        try {
            s.addStudent(s1);
            s.addStudent(s2);
        } catch(TooManyStudentsException e) {
            e.printStackTrace();
        }

        System.out.println(s); //GUI, teacher: Jan, students: Jasiek - 2 sem, Anna - 4 sem

    }
}
