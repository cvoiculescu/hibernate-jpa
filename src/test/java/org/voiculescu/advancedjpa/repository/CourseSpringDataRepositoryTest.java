package org.voiculescu.advancedjpa.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
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
    public void findById_coursePresent() {
        Optional<Course> byId = repository.findById(10001L);
        assertTrue(byId.isPresent());
    }

    @Test
    public void findById_courseNotPresent() {
        Optional<Course> byId = repository.findById(10008L);
        assertFalse(byId.isPresent());
    }

    @Test
    public void playing_around_with_SpringDataRepository() {
//        Course course = new Course().setName("My Course");
//        repository.save(course);
//        course.setName("My Course - Updated");
//        repository.save(course);
        repository.findAll().forEach(course -> log.info("{}", course));
        log.info("{}", repository.count());
    }

    @Test
    public void sort() {
        Sort sortName = Sort.by(Sort.Direction.DESC, "name");
        repository.findAll(sortName).forEach(course -> log.info("{}", course));
    }

}
