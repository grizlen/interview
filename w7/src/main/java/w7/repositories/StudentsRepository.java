package w7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import w7.entities.Student;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long> {
}
