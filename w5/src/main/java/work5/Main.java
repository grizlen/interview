package work5;

import work5.entities.Student;
import work5.sessions.Sessions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("alive");
        Optional<Student> student;
        List<Student> students;

        //create
        init();

        // read
        student = Sessions.getById(1L, Student.class);
        System.out.println("read: " + (student.isPresent() ? student.get() : "Not found"));

        students = Sessions.getAll(Student.class);
        System.out.println("read all");
        students.forEach(System.out::println);

        //update
        student = Sessions.getById(1L, Student.class);
        student.ifPresent(st -> {
            st.setName("FEDYA");
            Sessions.save(st);
        });
        student = Sessions.getById(1L, Student.class);
        System.out.println("update: " + (student.isPresent() ? student.get() : "Not found"));

        //delete
        student = Sessions.getById(1L, Student.class);
        student.ifPresent(Sessions::delete);
        student = Sessions.getById(1L, Student.class);
        System.out.println("past delete: " + (student.isPresent() ? student.get() : "Not found"));

        Sessions.close();
    }

    private static void init() {
        Sessions.save(new Student("VASYA", 5));

        List<Student> students = new ArrayList<>();
        for (int i = 2; i < 11; i++) {
            students.add(new Student("VASYA-" + i, 5));
        }
        Sessions.saveList(students);
    }
}
