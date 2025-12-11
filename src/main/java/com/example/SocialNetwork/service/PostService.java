package com.example.SocialNetwork.service;

import com.example.SocialNetwork.api.PageRs;
import com.example.SocialNetwork.error.NotFoundException;
import com.example.SocialNetwork.api.post.PostRq;
import com.example.SocialNetwork.api.post.PostRs;
import com.example.SocialNetwork.entity.PostEntity;
import com.example.SocialNetwork.entity.UserEntity;
import com.example.SocialNetwork.repository.PostRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;
    private final UserService typeService;

    public PostService(PostRepository repository, UserService typeService) {
        this.repository = repository;
        this.typeService = typeService;
    }


    //@Transactional(propagation = Propagation.MANDATORY)
    @Transactional(readOnly = true)
    public PostEntity getEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PostEntity.class, id));
    }

    @Transactional(readOnly = true)
    public PageRs<PostRs> getAll(Pageable pageable) {
        return PageRs.from(repository.findAll(pageable), PostRs::from);
    }

    @Transactional(readOnly = true)
    public PostRs get(Long id) {
        final PostEntity entity = getEntity(id);
        return PostRs.from(entity);
    }


    @Transactional(readOnly = true)
    public PageRs<PostRs> getByUser(Pageable pageable, Long userId) {return PageRs.from(repository.findByUser_IdOrderByIdDesc(pageable, userId), PostRs::from);}

    @Transactional(readOnly = true)
    public PageRs<PostRs> getNotByUser(Pageable pageable, Long userId) {
        return PageRs.from(repository.findByUser_IdNotOrderByIdDesc(pageable, userId), PostRs::from);
    }

    @Transactional
    public PostRs create(PostRq dto) {
        final UserEntity user = typeService.getEntity(dto.userId());
        PostEntity entity = new PostEntity(user, dto.postImageURL(), dto.postTextContent());
        entity = repository.save(entity);
        return PostRs.from(entity);
    }

    @Transactional
    public PostRs update(Long id, PostRq dto) {
        PostEntity entity = getEntity(id);

        entity.setUser(typeService.getEntity(dto.userId()));
        entity.setPostImageURL(dto.postImageURL());
        entity.setPostTextContent(dto.postTextContent());
        entity = repository.save(entity);
        return PostRs.from(entity);
    }

    @Transactional
    public PostRs delete(Long id) {
        final PostEntity entity = getEntity(id);
        repository.delete(entity);
        return PostRs.from(entity);
    }

}
