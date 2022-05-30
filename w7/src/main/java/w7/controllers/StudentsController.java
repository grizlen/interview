package w7.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import w7.entities.Student;
import w7.services.StudentsService;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class StudentsController {

    private final StudentsService studentsService;

    @GetMapping("/")
    public List<Student> getAll() {
        System.out.println("controller.getAll");
        return studentsService.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        System.out.println("controller.getById: " + id);
        Student student = studentsService.getByID(id);
        System.out.println("controller.getById return: " + student);
        return student;
    }

    @PostMapping("/")
    public void save(@RequestBody Student student) {
        System.out.println("controller.save: " + student);
        studentsService.save(student);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        System.out.println("controller.delete: " + id);
        studentsService.delete(id);
    }
}
