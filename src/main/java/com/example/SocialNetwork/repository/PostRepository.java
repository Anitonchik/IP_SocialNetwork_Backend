package com.example.SocialNetwork.repository;

import com.example.SocialNetwork.api.Post.PostDTO;
import com.example.SocialNetwork.entity.PostEntity;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository extends MapRepository<PostEntity> {
}
