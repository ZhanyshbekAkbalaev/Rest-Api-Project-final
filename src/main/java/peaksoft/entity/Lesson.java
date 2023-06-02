package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "lessons")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {
    @Id
    @GeneratedValue(generator = "lesson_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "lesson_gen",sequenceName = "lesson_seq",allocationSize = 1)
    private Long id;
    private String lessonName;
    @ManyToOne(cascade = {DETACH,MERGE,REFRESH,PERSIST})
    private Course course;
    @OneToMany(cascade = ALL,mappedBy = "lesson")
    private List<Task>tasks;

    public Lesson(String lessonName) {
        this.lessonName = lessonName;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
