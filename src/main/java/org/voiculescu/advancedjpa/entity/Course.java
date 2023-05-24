package org.voiculescu.advancedjpa.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(@NamedQuery(name = "query_get_all_courses", query = "select c from Course c"))
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

    public Course addReview(Review review){
        reviews.add(review);
        return this;
    }

    public Course removeReview(Review review){
        reviews.remove(review);
        return this;
    }

}
