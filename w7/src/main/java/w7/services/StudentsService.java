package w7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import w7.entities.Student;
import w7.repositories.StudentsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentsService {

    private final StudentsRepository studentsRepository;

    public List<Student> getAll() {
        System.out.println("service.getAll");
        return studentsRepository.findAll();
    }

    public Student getByID(Long id) {
        System.out.println("service.getById");
        return studentsRepository.findById(id).orElse(null);
    }

    public void save(Student student) {
        studentsRepository.save(student);
    }

    public void delete(Long id) {
        studentsRepository.deleteById(id);
    }
}
