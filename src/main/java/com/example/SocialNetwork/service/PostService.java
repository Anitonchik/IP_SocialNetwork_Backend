package com.example.SocialNetwork.service;

import com.example.SocialNetwork.api.NotFoundException;
import com.example.SocialNetwork.api.Post.PostDTO;
import com.example.SocialNetwork.api.Post.PostRq;
import com.example.SocialNetwork.api.Post.PostRs;
import com.example.SocialNetwork.entity.PostEntity;
import com.example.SocialNetwork.entity.UserEntity;
import com.example.SocialNetwork.mapper.PostMapper;
import com.example.SocialNetwork.repository.PostRepository;
import com.example.SocialNetwork.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;
    private final UserService typeService;
    private PostMapper mapper;

    public PostService(PostRepository repository, UserService typeService) {
        this.repository = repository;
        this.typeService = typeService;
    }

    public PostEntity getEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PostEntity.class, id));
    }

    public List<PostRs> getAll() {
        return mapper.toRsListDto(repository.findAll());
    }

    public PostRs get(Long id) {
        final PostEntity entity = getEntity(id);
        return mapper.toRsDto(entity);
    }

    public PostRs create(PostRq dto) {
        final UserEntity user = typeService.getEntity(dto.getUserId());
        PostEntity entity = new PostEntity(
                user,
                dto.getPostImageURL(),
                dto.getPostTextContent());
        entity = repository.save(entity);
        return mapper.toRsDto(entity);
    }

    public PostRs update(Long id, PostRq dto) {
        PostEntity entity = getEntity(id);

        entity.setUser(typeService.getEntity(dto.getUserId()));
        entity.setPostImageURL(dto.getPostImageURL());
        entity.setPostTextContent(dto.getPostTextContent());
        entity = repository.save(entity);
        return mapper.toRsDto(entity);
    }

    public PostRs delete(Long id) {
        final PostEntity entity = getEntity(id);
        repository.delete(entity);
        return mapper.toRsDto(entity);
    }

}
