package org.voiculescu.advancedjpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.voiculescu.advancedjpa.AdvancedJpaApplication;
import org.voiculescu.advancedjpa.entity.Course;

import java.util.List;

/**
 * "select c from Course c"
 * 1. Use CriteriaBuilder to create a criteria query
 * 2. Define root tables involved
 * 3. Define Predicates
 * 4. Add predicates
 * 5. Build TypedQuery
 */
@Slf4j
@SpringBootTest(classes = AdvancedJpaApplication.class)
class CriteriaQueryTest {

    @Autowired
    EntityManager em;

    @Test
    public void criteria_builder_all_course() {
        //*

        //1
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        //2.define root
        Root<Course> courseRoot = cq.from(Course.class);
        //5.
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

        /*/

        TypedQuery<Course> query = em.createQuery("select c from Course c", Course.class);

        //*/
        // ------- DO NOT REMOVE THIS UPPER COMMENT

        List<Course> resultList = query.getResultList();
        log.info("TypedQuery -> {}", resultList);
    }

    @Test
    public void criteria_builder_all_course_like() {
        //*
        // 1
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        // 2
        Root<Course> courseRoot = cq.from(Course.class);
        // 3,4 define predicates and add them
        Predicate nameLike50steps = cb.like(courseRoot.get("name"), "%50%");
        cq.where(nameLike50steps);
        // 5
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

        /*/
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.name like '%50%'", Course.class);
        //*/

        List<Course> resultList = query.getResultList();
        log.info("TypedQuery -> {}", resultList);
    }

    @Test
    public void criteria_builder_all_course_without_students() {
        //*
        // 1
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        // 2
        Root<Course> courseRoot = cq.from(Course.class);
        // 3,4 define predicates and add them
        Predicate noStudents = cb.isEmpty(courseRoot.get("students"));
        cq.where(noStudents);
        // 5
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

        /*/
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty", Course.class);
        //*/

        List<Course> resultList = query.getResultList();
        log.info("TypedQuery -> {}", resultList);
    }

}