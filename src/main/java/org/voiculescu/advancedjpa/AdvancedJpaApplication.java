package org.voiculescu.advancedjpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.voiculescu.advancedjpa.entity.Employee;
import org.voiculescu.advancedjpa.entity.FullTimeEmployee;
import org.voiculescu.advancedjpa.entity.PartTimeEmployee;
import org.voiculescu.advancedjpa.repository.EmployeeRepository;

import java.math.BigDecimal;

@Slf4j
@SpringBootApplication
public class AdvancedJpaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AdvancedJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
