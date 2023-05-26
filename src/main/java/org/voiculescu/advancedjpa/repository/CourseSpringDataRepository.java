package org.voiculescu.advancedjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.voiculescu.advancedjpa.entity.Course;

@Repository
public interface CourseSpringDataRepository extends JpaRepository<Course,Long> {



}
