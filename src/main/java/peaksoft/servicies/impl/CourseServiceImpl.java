package peaksoft.servicies.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.repositories.CompanyRepository;
import peaksoft.repositories.CourseRepository;
import peaksoft.servicies.CourseService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    @Override
    public SimpleResponse saveCourse(Long companyId, CourseRequest courseRequest) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new NoSuchElementException("Company with id " + companyId + " is not found!"));
        Course course = Course.builder()
                .courseName(courseRequest.getCourseName())
                .dateOfStart(courseRequest.getDateOfStart())
                .description(courseRequest.getDescription())
                .build();
        company.addCourse(course);
        course.setCompany(company);
        courseRepository.save(course);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Course by name is: %s is successfully saved.", courseRequest.getCourseName()))
                .build();
    }

    @Override
    public CourseResponse findByCourseId(Long courseId) {
        return courseRepository.getCourseById(courseId);
    }

    @Override
    public List<CourseResponse> findAllCoursesSortByDate(Long companyId) {
        return courseRepository.findAllByCompanyId(companyId);
    }

    @Override
    public SimpleResponse updateCourse(Long courseId, CourseRequest courseRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course with id " + courseId + " is not found!"));
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(courseRequest.getDateOfStart());
        course.setDescription(courseRequest.getDescription());
        courseRepository.save(course);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Course by name is: %s is successfully updated",courseRequest.getCourseName()))
                .build();
    }

    @Override
    public SimpleResponse deleteCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new NoSuchElementException("Course with id " + courseId + " is not found!");
        }
        courseRepository.deleteById(courseId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Course by id is: %s is successfully deleted.",courseId))
                .build();
    }
}
