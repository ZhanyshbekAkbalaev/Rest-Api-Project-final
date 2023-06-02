package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(generator = "task_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "task_gen",sequenceName = "task_seq",allocationSize = 1)
    private Long id;
    private String taskName;
    private String taskText;
    private LocalDate deadLine;
    @ManyToOne(cascade = {DETACH,MERGE,REFRESH,PERSIST})
    private Lesson lesson;

    public Task(String taskName, String taskText, LocalDate deadLine) {
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadLine = deadLine;
    }
}
