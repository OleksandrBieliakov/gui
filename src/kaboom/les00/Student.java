package kaboom.les00;

public class Student extends Person {

    private int group;


    public Student (String name, int age, int group) {
        super(name, age);
        this.group = group;
    }

    @Override
    public void sayHelloTo(Person other) {
        System.out.println("Good morning, " + other.getName() + "!");
    }

    public void sayHelloTo(Student other) {
        System.out.println("Yo, " + other.getName() + "!");
    }

    public int getGroup() {
        return group;
    }

    @Override
    public String getName() {
        return super.getName();
    }

}
