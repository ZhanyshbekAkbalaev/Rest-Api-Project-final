package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(generator = "course_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "course_gen", sequenceName = "course_seq", allocationSize = 1)
    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private String description;
    @ManyToOne(cascade = {DETACH, MERGE, PERSIST, REFRESH})
    private Company company;
    @ManyToOne(cascade = {DETACH, MERGE, REFRESH})
    private Instructor instructor;
    @ManyToMany(cascade = ALL, mappedBy = "courses")
    private List<Group> groups;
    @OneToMany(cascade = ALL,mappedBy = "course")
    private List<Lesson> lessons;

    public Course(String courseName, LocalDate dateOfStart, String description) {
        this.courseName = courseName;
        this.dateOfStart = dateOfStart;
        this.description = description;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }
}
