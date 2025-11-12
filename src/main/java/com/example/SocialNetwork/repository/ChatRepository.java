package com.example.SocialNetwork.repository;

import com.example.SocialNetwork.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    @Query(value = "SELECT * FROM chats " +
            "WHERE first_user_id = :userId " +
            "OR second_user_id = :userId ",
            nativeQuery = true)
    List<ChatEntity> findUsersChats(@Param("userId") Long userId);

    /*@Query(value = "SELECT * FROM chats " +
            "WHERE LEAST(first_user_id, second_user_id) = LEAST(:firstUserId, :secondUserId) " +
            "AND GREATEST(first_user_id, second_user_id) = GREATEST(:firstUserId, :secondUserId) " +
            "LIMIT 1", nativeQuery = true)*/

    @Query(value = "SELECT * FROM chats " +
            "WHERE first_user_id = :firstUserId " +
            "AND second_user_id = :secondUserId " +
            "LIMIT 1", nativeQuery = true)
    Optional<ChatEntity> findChatByUsers(@Param("firstUserId") Long firstUserId, @Param("secondUserId") Long secondUserId);

}
