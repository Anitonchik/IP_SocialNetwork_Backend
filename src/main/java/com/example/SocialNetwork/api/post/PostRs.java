package com.example.SocialNetwork.api.post;

import com.example.SocialNetwork.api.user.UserRs;
import com.example.SocialNetwork.entity.PostEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

public record PostRs (Long id, UserRs user, String postImageURL, String postTextContent, Date createdAt, Boolean isEdited) {

    public static PostRs from(PostEntity post) {
        return new PostRs(post.getId(), UserRs.from(post.getUser()), post.getPostImageURL(), post.getPostTextContent(), post.getCreatedAt(), post.getIsEdited());
    }

    public static List<PostRs> fromList(Iterable<PostEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(PostRs::from)
                .toList();
    }
}
