package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
