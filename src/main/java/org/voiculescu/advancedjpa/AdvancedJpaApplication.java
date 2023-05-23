package org.voiculescu.advancedjpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.voiculescu.advancedjpa.repository.StudentRepository;

@Slf4j
@SpringBootApplication
public class AdvancedJpaApplication implements CommandLineRunner {
    private final StudentRepository studentRepository;

    public AdvancedJpaApplication(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AdvancedJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        studentRepository.saveStudentWithPassport();
    }
}
