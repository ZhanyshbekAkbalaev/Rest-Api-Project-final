package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
