package org.voiculescu.advancedjpa.repository;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.advancedjpa.AdvancedJpaApplication;
import org.voiculescu.advancedjpa.entity.Course;
import org.voiculescu.advancedjpa.entity.Review;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AdvancedJpaApplication.class)
@Slf4j
class CourseRepositoryTest {

    @Autowired
    CourseRepository repository;
    @Autowired
    EntityManager entityManager;

    @Test
    public void findByIdTest() {
        Course byId = repository.findById(10001L);
        assertEquals("JPA in 50 Steps", byId.getName());
    }

    @Test
    @Transactional
    public void findByIdTest_firstLevelCache() {
        Course course = repository.findById(10001L);
        log.info("First course retrieved {}",course.hashCode());
        assertEquals("JPA in 50 Steps", course.getName());
        Course course1 = repository.findById(10001L);
        log.info("First course retrieved again {}",course1.hashCode());
        assertEquals("JPA in 50 Steps", course1.getName());

    }

    @Test
    @DirtiesContext
    public void deleteByIdTest() {
        repository.deleteById(10002L);
        assertNull(repository.findById(10002L));
    }

    @Test
    @DirtiesContext
    public void saveTest(){

        Course course = repository.save(new Course().setName("Test"));
        assertNotNull(course.getId());

        Course course1 = repository.findById(10001L);
        course1.setName("Test");
        repository.save(course1);
        Course storedCourse1 = repository.findById(course1.getId());
        assertEquals("Test",storedCourse1.getName());
    }

    @Test
    @DirtiesContext
    public void playWith(){
        repository.playWithEntityManager();
    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse(){
        Course course = repository.findById(10001L);
        course.getReviews().forEach(review->log.info("{}",review));
    }

    @Test
    @Transactional
    public void retrieveCourseForReview(){
        Review review = entityManager.find(Review.class,50001L);
        log.info("{}",review.getCourse());
    }

}
