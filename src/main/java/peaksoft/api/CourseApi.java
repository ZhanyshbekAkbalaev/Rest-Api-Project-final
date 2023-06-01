package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.servicies.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseApi {
    private final CourseService courseService;

    @Autowired
    public CourseApi(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/{companyId}")
    public SimpleResponse saveCourse(@PathVariable("companyId") Long companyId, @RequestBody CourseRequest courseRequest) {
        return courseService.saveCourse(companyId, courseRequest);
    }

    @GetMapping("/{companyId}")
    public List<CourseResponse> getAll(@PathVariable("companyId") Long companyId) {
        return courseService.findAllCoursesSortByDate(companyId);
    }

    @GetMapping("/find{courseId}")
    public CourseResponse findCourseById(@PathVariable("courseId") Long courseId) {
        return courseService.findByCourseId(courseId);
    }

    @DeleteMapping("/delete/{courseId}")
    public SimpleResponse deleteCourse(@PathVariable Long courseId) {
        return courseService.deleteCourse(courseId);
    }
}
