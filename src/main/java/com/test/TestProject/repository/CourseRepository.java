package com.test.TestProject.repository;

import com.test.TestProject.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity,Integer> {
    public CourseEntity findByName(String name);

    @Query("select c from CourseEntity c where c.name=:n")
    public CourseEntity finCourseByName(@Param("n") String name);

    @Modifying
    @Transactional
    @Query("update CourseEntity c set c.name = :name where c.id = :id")
    public void updateCourseName(@Param("name")String name, @Param("id")Integer id);
}
