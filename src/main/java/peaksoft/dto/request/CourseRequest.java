package peaksoft.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
public class CourseRequest {
    private String courseName;
    private LocalDate dateOfStart;
    private String description;
}
