package peaksoft.servicies;

import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;

public interface InstructorService {
    SimpleResponse saveInstructor(InstructorRequest instructorRequest);

    InstructorResponse findByInstructorId(Long instructorId);

    List<InstructorResponse> findAllInstructors();

    SimpleResponse updateInstructor(Long instructorId, InstructorRequest instructorRequest);

    SimpleResponse deleteInstructor(Long instructorId);

    SimpleResponse assignInstructorToCompany(Long instructorId, Long companyId);

    SimpleResponse assignInstructorToCourse(Long instructorId, Long courseId);
}
