package org.voiculescu.advancedjpa.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import org.voiculescu.advancedjpa.AdvancedJpaApplication;
import org.voiculescu.advancedjpa.entity.Course;
import org.voiculescu.advancedjpa.entity.Review;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AdvancedJpaApplication.class)
@Slf4j
class PerformanceTuningTest {

    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    public void creatingNPlusOneProblem() {
        TypedQuery<Course> allCourses = entityManager.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> courses = allCourses.getResultList();
        for(Course course:courses){
            log.info("Course -> {} Students: {}",course,course.getStudents());
        }
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_EntityGraph() {
        EntityGraph<Course> graph = entityManager.createEntityGraph(Course.class);
        graph.addSubgraph("students");
        TypedQuery<Course> allCourses = entityManager.createNamedQuery("query_get_all_courses", Course.class)
                .setHint("javax.persistence.loadgraph",graph);
        List<Course> courses = allCourses.getResultList();
        for(Course course:courses){
            log.info("Course -> {} Students: {}",course,course.getStudents());
        }
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_JoinFetch() {
        EntityGraph<Course> graph = entityManager.createEntityGraph(Course.class);
        graph.addSubgraph("students");
        TypedQuery<Course> allCourses = entityManager.createNamedQuery("query_get_all_courses_join_fetch", Course.class);
        List<Course> courses = allCourses.getResultList();
        for(Course course:courses){
            log.info("Course -> {} Students: {}",course,course.getStudents());
        }
    }

}