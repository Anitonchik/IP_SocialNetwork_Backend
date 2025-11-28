package com.example.SocialNetwork.repository;

import com.example.SocialNetwork.entity.Report;
import com.example.SocialNetwork.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);
    Optional<UserEntity> findByPhone(String phone);

    @Query(value = "SELECT EXTRACT(DAY FROM c.CREATED_AT) as date, " +
            "COUNT(*) as chatCount " +
            "FROM Chats c " +
            "WHERE (c.FIRST_USER_ID = :userId OR c.SECOND_USER_ID = :userId) " +
            "GROUP BY  EXTRACT(DAY FROM c.CREATED_AT) " +
            "ORDER BY date", nativeQuery = true)
    List<Report> getUserStatistics(@Param("userId") Long userId);

}
