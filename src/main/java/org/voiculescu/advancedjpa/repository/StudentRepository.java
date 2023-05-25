package org.voiculescu.advancedjpa.repository;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.advancedjpa.entity.Course;
import org.voiculescu.advancedjpa.entity.Passport;
import org.voiculescu.advancedjpa.entity.Student;

@Slf4j
@Repository
@Transactional
public class StudentRepository {

    private final EntityManager entityManager;

    @Autowired
    public StudentRepository(EntityManager em) {
        this.entityManager = em;
    }

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }


    public void deleteById(Long id) {
        Student byId = findById(id);
        entityManager.remove(byId);
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            entityManager.persist(student);
        } else {
            entityManager.merge(student);
        }
        return student;
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport()
                .setNumber("Z12345");
        entityManager.persist(passport);
        Student student = new Student()
                .setName("Mike")
                .setPassport(passport);
        entityManager.persist(student);
    }

    public void insertStudentAndCourseHardcoded() {
        Student student = new Student().setName("Test");
        Course course = new Course().setName("Microservices");
        entityManager.persist(student);
        entityManager.persist(course);
        student.addCourse(course);
        course.addStudent(student);
        entityManager.persist(course);
    }

    public void insertStudentAndCourse(Student student, Course course) {
        student.addCourse(course);
        course.addStudent(student);
        entityManager.persist(student);
        entityManager.persist(course);
    }

}