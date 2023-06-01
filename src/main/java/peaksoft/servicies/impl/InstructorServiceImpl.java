package peaksoft.servicies.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.repositories.CompanyRepository;
import peaksoft.repositories.CourseRepository;
import peaksoft.repositories.InstructorRepository;
import peaksoft.servicies.InstructorService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository, CompanyRepository companyRepository, CourseRepository courseRepository) {
        this.instructorRepository = instructorRepository;
        this.companyRepository = companyRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public SimpleResponse saveInstructor(InstructorRequest instructorRequest) {
        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        instructorRepository.save(instructor);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Instructor by name is: %s is successfully saved.", instructor.getFirstName()))
                .build();
    }

    @Override
    public InstructorResponse findByInstructorId(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NullPointerException("Instructor with id " + instructorId + " is not found!"));
        return new InstructorResponse(
                instructor.getId(),
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getPhoneNumber(),
                instructor.getSpecialization()
        );
    }

    @Override
    public List<InstructorResponse> findAllInstructors() {
        return instructorRepository.findAllInstructors();
    }

    @Override
    public SimpleResponse updateInstructor(Long instructorId, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Instructor with id " + instructorId + " is not found!"));
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
        instructor.setSpecialization(instructorRequest.getSpecialization());
        instructorRepository.save(instructor);
        return SimpleResponse
                .builder()
                .status(HttpStatus.OK)
                .message(String.format("Instructor by name is %s successfully updated!", instructorRequest.getFirstName()))
                .build();
    }

    @Override
    public SimpleResponse deleteInstructor(Long instructorId) {
        instructorRepository.deleteById(instructorId);
        return SimpleResponse
                .builder()
                .status(HttpStatus.OK)
                .message(String.format("Instructor by id is %s is successfully deleted!!!", instructorId))
                .build();
    }

    @Override
    public SimpleResponse assignInstructorToCompany(Long instructorId, Long companyId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Instructor with id " + instructorId + " is not found!"));
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new NoSuchElementException("Company with id " + companyId + " is not found!"));
        instructor.addCompany(company);
        company.addInstructor(instructor);
        return SimpleResponse
                .builder()
                .status(HttpStatus.OK)
                .message(String.format("instructor by name is %s and Company by name is %s is successfully assigned", instructor.getFirstName(), company.getName()))
                .build();
    }

    @Override
    public SimpleResponse assignInstructorToCourse(Long instructorId, Long courseId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Instructor with id " + instructorId + " is not found!"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course with id " + courseId + " is not found!"));
        instructor.addCourse(course);
        course.setInstructor(instructor);
        return SimpleResponse
                .builder()
                .status(HttpStatus.OK)
                .message(String.format("instructor by name is %s and Course by name is %s is successfully assigned", instructor.getFirstName(), course.getCourseName()))
                .build();
    }

}
