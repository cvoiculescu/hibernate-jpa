package org.voiculescu.advancedjpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.voiculescu.advancedjpa.AdvancedJpaApplication;
import org.voiculescu.advancedjpa.entity.Course;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AdvancedJpaApplication.class)
class CourseRepositoryTest {

    @Autowired
    CourseRepository repository;

    @Test
    public void findByIdTest() {
        Course byId = repository.findById(10001L);
        assertEquals("JPA in 50 Steps", byId.getName());
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

}