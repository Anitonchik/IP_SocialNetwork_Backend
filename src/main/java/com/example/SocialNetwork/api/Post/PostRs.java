package com.example.SocialNetwork.api.Post;

import com.example.SocialNetwork.api.user.UserRq;
import com.example.SocialNetwork.api.user.UserRs;
import com.example.SocialNetwork.entity.PostEntity;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.stream.StreamSupport;

public record PostRs (Long id, UserRs user, String postImageURL, String postTextContent) {

    public static PostRs from(PostEntity post) {
        return new PostRs(post.getId(), UserRs.from(post.getUser()), post.getPostImageURL(), post.getPostTextContent());
    }

    public static List<PostRs> fromList(Iterable<PostEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(PostRs::from)
                .toList();
    }
}
