package peaksoft.servicies;


import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;

public interface CourseService {
    SimpleResponse saveCourse(Long companyId,CourseRequest courseRequest);
    CourseResponse findByCourseId(Long courseId);
    List<CourseResponse> findAllCoursesSortByDate(Long companyId);
    SimpleResponse updateCourse(Long courseId,CourseRequest courseRequest);
    SimpleResponse deleteCourse(Long courseId);
}
