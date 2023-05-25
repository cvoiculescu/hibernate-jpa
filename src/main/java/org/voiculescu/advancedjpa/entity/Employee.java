package org.voiculescu.advancedjpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "employee")
public abstract class Employee {

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

}