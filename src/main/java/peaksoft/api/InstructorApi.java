package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.servicies.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorApi {
    private final InstructorService instructorService;

    @Autowired
    public InstructorApi(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public List<InstructorResponse> instructorResponses() {
        return instructorService.findAllInstructors();
    }

    @PostMapping
    public SimpleResponse saveInstructor(@RequestBody InstructorRequest instructorRequest) {
        return instructorService.saveInstructor(instructorRequest);
    }

    @GetMapping("/{instructorId}")
    public InstructorResponse getInstructorById(@PathVariable Long instructorId) {
        return instructorService.findByInstructorId(instructorId);
    }

    @PutMapping("/{instructorId}")
    public SimpleResponse updateInstructor(@PathVariable Long instructorId, @RequestBody InstructorRequest instructorRequest) {
        return instructorService.updateInstructor(instructorId, instructorRequest);
    }

    @DeleteMapping("/{instructorId}")
    public SimpleResponse deleteInstructor(@PathVariable Long instructorId) {
        return instructorService.deleteInstructor(instructorId);
    }

    @PostMapping("/assignCompany/{instructorId}/{companyId}")
    public SimpleResponse assignInstructorToCompany(@PathVariable Long instructorId, @PathVariable Long companyId) {
        return instructorService.assignInstructorToCompany(instructorId, companyId);
    }



    @PostMapping("/assignCourse/{instructorId}/{courseId}")
    public SimpleResponse assignInstructorToCourse(@PathVariable Long instructorId, @PathVariable Long courseId) {
        return instructorService.assignInstructorToCourse(instructorId, courseId);
    }
}
