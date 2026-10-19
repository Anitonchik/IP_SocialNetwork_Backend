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

    Optional<ChatEntity> findByFirstUser_IdAndSecondUser_Id(Long firstUserId, Long secondUserId);

    void deleteAllByFirstUserId(Long userId);
    void deleteAllBySecondUserId(Long userId);

}
