package peaksoft.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
public class TaskResponse {
    private Long id;
    private String taskName;
    private String taskText;
    private LocalDate deadLine;
}
