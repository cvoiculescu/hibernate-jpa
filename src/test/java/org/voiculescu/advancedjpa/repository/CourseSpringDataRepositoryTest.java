package org.voiculescu.advancedjpa.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.voiculescu.advancedjpa.AdvancedJpaApplication;
import org.voiculescu.advancedjpa.entity.Course;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(classes = AdvancedJpaApplication.class)
class CourseSpringDataRepositoryTest {

    @Autowired
    CourseSpringDataRepository repository;

    @Test
    public void findById_coursePresent(){
        Optional<Course> byId = repository.findById(10001L);
        assertTrue(byId.isPresent());
    }

    @Test
    public void findById_courseNotPresent(){
        Optional<Course> byId = repository.findById(10008L);
        assertFalse(byId.isPresent());
    }

}
