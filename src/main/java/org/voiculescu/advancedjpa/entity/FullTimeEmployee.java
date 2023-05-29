package org.voiculescu.advancedjpa.entity;

import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class FullTimeEmployee extends Employee {

    private BigDecimal salary;

    protected FullTimeEmployee() {
    }

    public FullTimeEmployee(String name, BigDecimal salary) {
        super(name);
        this.salary = salary;
    }
}