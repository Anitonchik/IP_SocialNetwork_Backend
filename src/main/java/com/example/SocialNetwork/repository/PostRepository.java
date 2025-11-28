package com.example.SocialNetwork.repository;

import com.example.SocialNetwork.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByUser_Id(Long userId);
    List<PostEntity> findByUser_IdNot(Long userId);
}
