package org.voiculescu.advancedjpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.advancedjpa.entity.Course;
import org.voiculescu.advancedjpa.entity.Review;
import org.voiculescu.advancedjpa.repository.CourseRepository;
import org.voiculescu.advancedjpa.repository.StudentRepository;

import java.util.List;

@Slf4j
@SpringBootApplication
public class AdvancedJpaApplication implements CommandLineRunner{

//    @Autowired
//    StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(AdvancedJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        studentRepository.insertStudentAndCourseHardcoded();
    }
}
