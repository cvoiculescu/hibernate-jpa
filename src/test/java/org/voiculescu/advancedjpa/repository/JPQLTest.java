package org.voiculescu.advancedjpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.voiculescu.advancedjpa.AdvancedJpaApplication;
import org.voiculescu.advancedjpa.entity.Course;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(classes = AdvancedJpaApplication.class)
class JPQLTest {

    @Autowired
    EntityManager em;

    @Test
    public void findByIdTest() {
        List<Course> resultList = em.createQuery("select c from Course c", Course.class).getResultList();
        log.info("{}", resultList);
        em.createQuery("select c from Course c where name ilike 'teps'", Course.class).getResultList();
        TypedQuery<Course> queryGetAllCourses = em.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> resultList1 = queryGetAllCourses.getResultList();
    }

    @Test
    public void jpql_courses_without_students() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty", Course.class);
        List<Course> courses = query.getResultList();
        log.info("Courses -> {}",courses);
    }

}