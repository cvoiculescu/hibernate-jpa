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

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AdvancedJpaApplication.class)
@Slf4j
class NativeQueryTest {

    @Autowired
    EntityManager entityManager;

    @Test
    public void native_query_basic() {
        Query query = entityManager.createNativeQuery("SELECT * FROM COURSE WHERE is_deleted=false", Course.class);
        List resultList = query.getResultList();
        log.info("Select all from course {}", resultList);
    }

}