package com.example.SocialNetwork.repository;

import com.example.SocialNetwork.entity.ChatEntity;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    @Query(value = "SELECT * FROM chats " +
            "WHERE LEAST(first_user_id, second_user_id) = :userId " +
            "OR GREATEST(first_user_id, second_user_id) = :userId ",
            nativeQuery = true)
    List<ChatEntity> findUsersChats(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM chats " +
            "WHERE LEAST(first_user_id, second_user_id) = LEAST(:firstUserId, :secondUserId) " +
            "AND GREATEST(first_user_id, second_user_id) = GREATEST(:firstUserId, :secondUserId) " +
            "LIMIT 1", nativeQuery = true)
    Optional<ChatEntity> findChatByUsers(@Param("firstUserId") Long firstUserId, @Param("secondUserId") Long secondUserId);

}
