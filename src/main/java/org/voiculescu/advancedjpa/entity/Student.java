package org.voiculescu.advancedjpa.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

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
    @JoinColumn
    private Passport passport;
}
