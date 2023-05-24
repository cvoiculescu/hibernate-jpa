package org.voiculescu.advancedjpa.repository;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.advancedjpa.entity.Course;
import org.voiculescu.advancedjpa.entity.Review;

import java.util.List;

@Slf4j
@Repository
@Transactional
public class CourseRepository {

    private final EntityManager em;

    @Autowired
    public CourseRepository(EntityManager em) {
        this.em = em;
    }

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }


    public void deleteById(Long id) {
        Course byId = findById(id);
        em.remove(byId);
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }
        return course;
    }

    public void playWithEntityManager() {
        log.info("play with em start");
        Course course1 = new Course().setName("Course1");
        em.persist(course1);
        em.flush();
        course1.setName("Test1");
        em.flush();
        Course course2 = new Course().setName("Course2");
        em.persist(course2);
        em.flush();
        course2.setName("Test2");
        em.refresh(course2);
        em.flush();
        em.clear();
    }

    public void addHardcodedReviewsForCourse() {
        // retrieve course
        Course course = em.find(Course.class, 10002L);
        log.info("Reviews: {}", course.getReviews());
        // create and set relationships
        Review review1 = new Review().setRating("5").setDescription("Nice course").setCourse(course);
        Review review2 = new Review().setRating("5").setDescription("Nice course").setCourse(course);
        course.addReview(review1);
        course.addReview(review2);
        // persist reviews
        em.persist(review1);
        em.persist(review2);
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        Course course = em.find(Course.class, courseId);
        log.info("Reviews: {}", course.getReviews());
        reviews.forEach(review -> {
            review.setCourse(course);
            course.addReview(review);
            em.persist(review);
        });

    }

}