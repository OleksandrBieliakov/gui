package kaboom.les00;

public class Subject {

    private Person teacher;
    private String name;
    private Student[] students = new Student[15];
    private int count = 0;

    public Subject(String name) {
        this.name = name;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public void addStudent(Student student) throws  TooManyStudentsException {
            if(count >= students.length) throw new TooManyStudentsException();
            students[count++] = student;
    }

    private String getStudents() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < count; ++i) {
            sb.append(students[i].getName());
            sb.append(" - ");
            sb.append(students[i].getGroup());
            sb.append(" sem, ");
        }

        sb.delete(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1);

        return sb.toString();
    }

    @Override
    public String toString() {
        return name + ", teacher: " + teacher.getName() + ", students: " + this.getStudents();
    }

}
