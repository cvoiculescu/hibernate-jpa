package org.voiculescu.advancedjpa.entity;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private ReviewRating rating = ReviewRating.FIVE;

    @ManyToOne
    private Course course;

}
