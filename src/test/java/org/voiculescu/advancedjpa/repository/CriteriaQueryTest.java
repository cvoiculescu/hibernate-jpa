package org.voiculescu.advancedjpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.voiculescu.advancedjpa.AdvancedJpaApplication;
import org.voiculescu.advancedjpa.entity.Course;

import java.util.List;

@Slf4j
@SpringBootTest(classes = AdvancedJpaApplication.class)
class CriteriaQueryTest {

    @Autowired
    EntityManager em;

    @Test
    public void jpql_basic() {
        /* "select c from Course c"
            1. Use CriteriaBuilder to create a criteria query
            2. Define root tables involved
            3. Define Predicates
            4. Add predicates
            5. Build TypedQuery
         */


        //* -------- Remove one / for other behaviour

        //1
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        //2.define root
        Root<Course> courseRoot = cq.from(Course.class);
        //5.
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

        /*/
        // ------- DO NOT REMOVE THIS UPPER COMMENT

        TypedQuery<Course> query = em.createQuery("select c from Course c", Course.class);

        //*/
        // ------- DO NOT REMOVE THIS UPPER COMMENT

        List<Course> resultList = query.getResultList();
        log.info("TypedQuery -> {}", resultList);

    }

}