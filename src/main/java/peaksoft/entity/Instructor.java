package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(generator = "instructor_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "instructor_gen",sequenceName = "instructor_seq",allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String specialization;
    @ManyToMany(cascade = {DETACH,MERGE,PERSIST,REFRESH},mappedBy = "instructors")
    private List<Company> companies;
    @OneToMany(cascade = {REFRESH,DETACH,MERGE},mappedBy = "instructor")
    private List<Course>courses;

    public Instructor(String firstName, String lastName, String phoneNumber, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }

    public void addCompany(Company company) {
        companies.add(company);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }
}