package org.voiculescu.advancedjpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.advancedjpa.entity.Course;
import org.voiculescu.advancedjpa.entity.Review;
import org.voiculescu.advancedjpa.repository.CourseRepository;

import java.util.List;

@Slf4j
@SpringBootApplication
public class AdvancedJpaApplication implements CommandLineRunner {

    private final CourseRepository courseRepository;

    public AdvancedJpaApplication(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AdvancedJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Review review1 = new Review().setRating("5").setDescription("Nice course");
        Review review2 = new Review().setRating("5").setDescription("Nice course");
        courseRepository.addReviewsForCourse(10001L, List.of(review1, review2));
    }
}
