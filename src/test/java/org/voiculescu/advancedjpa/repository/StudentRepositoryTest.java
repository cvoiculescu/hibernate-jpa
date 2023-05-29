package org.voiculescu.advancedjpa.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.voiculescu.advancedjpa.AdvancedJpaApplication;
import org.voiculescu.advancedjpa.entity.Address;
import org.voiculescu.advancedjpa.entity.Course;
import org.voiculescu.advancedjpa.entity.Passport;
import org.voiculescu.advancedjpa.entity.Student;

@Slf4j
@SpringBootTest(classes = AdvancedJpaApplication.class)
class StudentRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    StudentRepository repository;

    @Test
    @Transactional // creates a persistence context
    @DirtiesContext
    public void someTestToUnderstandPersistenceContext() {
        // Retrieve student
        Student student = entityManager.find(Student.class, 20001L);
        // Retrieve passport
        Passport passport = student.getPassport();
        // update passport
        passport.setNumber("E1234567");
        // update student
        student.setName("Test");
    }

    @Test
    @Transactional
    public void retrievePassportAndAssociatedStudent() {
        Passport passport = entityManager.find(Passport.class, 40001L);
        log.info("password {}", passport);
        log.info("student {}", passport.getStudent());
    }

    @Nested
    @DisplayName("Many To Many Testing for Students - Courses")
    class ManyToMany {
        @Test
        @Transactional
        public void retrieveStudentAndCourses() {
            Student student = repository.findById(20001L);
            log.info("{}", student);
            student.getCourses().forEach(course -> log.info("{}", course));
        }

        @Test
        @Transactional
        public void retrieveCourseAndStudents() {
            Course course = entityManager.find(Course.class, 10001L);
            log.info("{}", course);
            course.getStudents().forEach(student -> log.info("{}", student));
        }

        @Test
        public void insertStudentAndCourse() {
            Student student = new Student().setName("Test");
            Course course = new Course().setName("Microservices");
            repository.insertStudentAndCourse(student, course);
        }

        @Test
        @Transactional
        public void add_Student_Address() {
            Student student = entityManager.find(Student.class, 20001L);
            Address address = new Address("line1", "line2", "Brasov");
            student.setAddress(address);
            entityManager.flush();
        }

    }

}