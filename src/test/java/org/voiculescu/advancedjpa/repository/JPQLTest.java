package org.voiculescu.advancedjpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
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
        log.info("Courses -> {}", courses);
    }

    @Test
    public void jpql_courses_without_at_least_2_students() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where size(c.students) >= 2", Course.class);
        List<Course> courses = query.getResultList();
        log.info("Courses -> {}", courses);
    }

    @Test
    @Transactional
    public void jpql_courses_orderBy_number_of_students() {
        TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students) desc", Course.class);
        List<Course> courses = query.getResultList();
        courses.forEach(course -> log.info("{}", course));
    }

    @Test
    @Transactional
    public void jpql_courses_like_50_steps() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.name like '%50%'", Course.class);
        List<Course> courses = query.getResultList();
        courses.forEach(course -> log.info("{}", course));
    }

    @Test
    @Transactional
    public void jpql_join() {
//        JOIN -> select c, s from Course c JOIN c.students s (ignore courses without students)
//        LEFT JOIN -> select c, s from Course c LEFT JOIN c.students s (include also courses without students)
//        CROSS JOIN -> select c,s from Course c, Student s (mixed join -> no relations considered 3ST + 2CRS => 3x2=6)
        log.info("---------- JOIN ----------");
        Query joinQuery = em.createQuery("select c, s from Course c JOIN c.students s");
        ((List<Object[]>)joinQuery.getResultList()).forEach(obj -> log.info("Course: {} || Student: {}", obj[0], obj[1]));
        log.info("------- LEFT JOIN --------");
        Query leftJoinQuery = em.createQuery("select c, s from Course c LEFT JOIN c.students s");
        ((List<Object[]>)leftJoinQuery.getResultList()).forEach((Object[] obj) -> log.info("Course: {} || Student: {}", obj[0], obj[1]));
        log.info("------- CROSS JOIN --------");
        Query corssJoinQuery = em.createQuery("select c, s from Course c, Student s");
        ((List<Object[]>)corssJoinQuery.getResultList()).forEach((Object[] obj) -> log.info("Course: {} || Student: {}", obj[0], obj[1]));
    }

}