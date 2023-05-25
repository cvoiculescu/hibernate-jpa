package org.voiculescu.advancedjpa.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class PartTimeEmployee extends Employee {

    private BigDecimal hourlyWage;

    protected PartTimeEmployee(String name, BigDecimal hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }
}