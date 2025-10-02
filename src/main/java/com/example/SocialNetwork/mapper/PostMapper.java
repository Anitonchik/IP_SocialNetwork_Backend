package com.example.SocialNetwork.mapper;

import com.example.SocialNetwork.api.Post.PostRq;
import com.example.SocialNetwork.api.Post.PostRs;
import com.example.SocialNetwork.api.user.UserRq;
import com.example.SocialNetwork.entity.PostEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class PostMapper {
    private final UserMapper userMapper;

    public PostMapper(UserMapper userMapper) { this.userMapper = userMapper; }

    public PostRq toRgDto(UserRq user, String postImageURL, String postTextContent) {
        final PostRq dto = new PostRq();
        dto.setUser(user);
        dto.setPostImageURL(postImageURL);
        dto.setPostTextContent(postTextContent);
        return dto;
    }

    public PostRs toRsDto(PostEntity postEntity){
        final PostRs dto = new PostRs();
        var userRs = userMapper.toRsDto(postEntity.getUser());
        dto.setUser(userRs);
        dto.setPostImageURL(postEntity.getPostImageURL());
        dto.setPostTextContent(postEntity.getPostTextContent());
        return dto;
    }

    public List<PostRs> toRsListDto(Iterable<PostEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::toRsDto)
                .toList();
    }
}
