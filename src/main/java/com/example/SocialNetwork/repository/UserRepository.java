package com.example.SocialNetwork.repository;

import com.example.SocialNetwork.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);
    Optional<UserEntity> findByPhone(String phone);

    /*@Query("select cs.course as course, " +
            "count(cs) as students, " +
            "avg(cs.grade) as avgGrade, " +
            "min(cs.grade) as minGrade, " +
            "max(cs.grade) as maxGrade " +
            "from CourseEntity c inner join c.courseStudents cs " +
            "where cs.course.id = :courseId group by cs.course " +
            "having count(cs) > 0")
    CourseStatsProjection getCourseStatistics(@Param("courseId") Long courseId);*/

}
