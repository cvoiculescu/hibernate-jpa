package org.voiculescu.advancedjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({@NamedQuery(name = "query_get_all_courses",
            query = "select c from Course c"),
        @NamedQuery(name = "query_get_all_courses_join_fetch",
                query = "select c from Course c join fetch c.students s")}
)
@Cacheable
@SQLDelete(sql = "update course set is_deleted=true where id=?")
@Where(clause = "is_deleted=false")
@Slf4j
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "course")
    @Setter(AccessLevel.NONE)
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    public void addReview(Review review) {
        reviews.add(review);
    }

    public Course removeReview(Review review) {
        reviews.remove(review);
        return this;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    private boolean isDeleted;

    @PreRemove
    public void preRemove() {
        log.info("setting isDeleted=true");
        isDeleted = true;
    }
}
