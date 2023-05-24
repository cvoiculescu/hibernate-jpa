package org.voiculescu.advancedjpa.repository;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.advancedjpa.entity.Passport;
import org.voiculescu.advancedjpa.entity.Student;

@Slf4j
@Repository
@Transactional
public class StudentRepository {

    private final EntityManager em;

    @Autowired
    public StudentRepository(EntityManager em) {
        this.em = em;
    }

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }


    public void deleteById(Long id) {
        Student byId = findById(id);
        em.remove(byId);
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
        return student;
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport()
                .setNumber("Z12345");
        em.persist(passport);
        Student student = new Student()
                .setName("Mike")
                .setPassport(passport);
        em.persist(student);
    }

}