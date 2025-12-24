package com.example.SocialNetwork.repository;

import com.example.SocialNetwork.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Page<PostEntity> findByUser_IdOrderByIdDesc(Pageable pageable, Long userId);
    Page<PostEntity> findByUser_IdNotOrderByIdDesc(Pageable pageable, Long userId);
    void deleteAllByUser_Id(Long userId);
}
