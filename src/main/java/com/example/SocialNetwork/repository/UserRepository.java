package com.example.SocialNetwork.repository;

import com.example.SocialNetwork.entity.Report;
import com.example.SocialNetwork.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);
    Optional<UserEntity> findByPhone(String phone);
    Page<UserEntity> findByIdNot(Pageable pageable, Long userId);

    /*@Query("SELECT c.createdAt as date, " +
            "COUNT(*) as chatCount " +
            "FROM Chats c " +
            "WHERE (c.firstUser.id = :userId OR c.secondUser.id = :userId) " +
            "GROUP c.createdAt" +
            "ORDER BY date ")*/
    @Query(value = "SELECT EXTRACT(DAY FROM c.CREATED_AT) as date, " +
            "COUNT(*) as chatCount " +
            "FROM Chats c " +
            "WHERE (c.FIRST_USER_ID = :userId OR c.SECOND_USER_ID = :userId) " +
            "GROUP BY  EXTRACT(DAY FROM c.CREATED_AT) " +
            "ORDER BY date", nativeQuery = true)
    List<Report> getUserStatistics(@Param("userId") Long userId);

    Page<UserEntity> findByUserNameContainingIgnoreCase(String userName, Pageable pageable);

    Page<UserEntity> findByUserNameContainingIgnoreCaseAndIdNot(String usernamePart, Long userAuthId, Pageable pageable);
    Page<UserEntity> findByUserNameContainingIgnoreCaseAndIdNotOrderByUserNameAsc(String usernamePart, Long userAuthId, Pageable pageable);
    Page<UserEntity> findByIdNotOrderByUserNameAsc(Long userAuthId, Pageable pageable);
}
