package org.voiculescu.advancedjpa.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Student", indexes = {
        @Index(name = "idx_student_id", columnList = "id")
})
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    private String name;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passport_id")
    //    @OneToOne(fetch = FetchType.EAGER) // default fetching
    private Passport passport;

    @ManyToMany(mappedBy = "students")
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    List<Course> courses = new ArrayList<>();

    @Embedded
    private Address address;

    public void addCourse(Course course){
        courses.add(course);
    }

    public void removeCourse(Course course){
        courses.remove(course);
    }
}
