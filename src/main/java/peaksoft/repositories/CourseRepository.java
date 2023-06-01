package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.CourseResponse;
import peaksoft.entity.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select new peaksoft.dto.response.CourseResponse(c.id, c.courseName, c.dateOfStart, c.description ) from Course c where c.company.id =?1")
    List<CourseResponse> findAllByCompanyId(Long companyId);

    @Query("select new peaksoft.dto.response.CourseResponse(c.id,c.courseName,c.dateOfStart,c.description)from Course c where c.id = ?1")
    CourseResponse getCourseById(Long courseId);
}
