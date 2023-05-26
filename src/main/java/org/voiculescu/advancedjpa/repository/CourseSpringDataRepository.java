package org.voiculescu.advancedjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.voiculescu.advancedjpa.entity.Course;

import java.util.List;

@Repository
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);
    @Query("select c from Course c where upper(c.name) like upper(?1)")
    List<Course> findByNameLikeIgnoreCase(String name);
    List<Course> countCourseByName(String name);
    List<Course> findByNameAndId(String name, Long id);
    List<Course> findByNameOrderByIdDesc(String name);

}
