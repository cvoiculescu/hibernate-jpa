package org.voiculescu.advancedjpa.repository;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.advancedjpa.entity.Course;

@Slf4j
@Repository
@Transactional
public class CourseRepository {

    final EntityManager em;

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

    public void playWithEntityManager(){
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

}