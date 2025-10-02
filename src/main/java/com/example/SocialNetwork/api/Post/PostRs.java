package com.example.SocialNetwork.api.Post;

import com.example.SocialNetwork.api.user.UserRq;
import com.example.SocialNetwork.api.user.UserRs;
import jakarta.validation.constraints.NotNull;

public class PostRs {
    private Long id;
    @NotNull
    private UserRs user;
    private String postImageURL;
    private String postTextContent;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UserRs getUser() {
        return user;
    }

    public void setUser(UserRs user) {
        this.user = user;
    }

    public String getPostImageURL() {
        return postImageURL;
    }

    public void setPostImageURL(String postImageURL) {
        this.postImageURL = postImageURL;
    }

    public String getPostTextContent() {
        return postTextContent;
    }

    public void setPostTextContent(String postTextContent) {
        this.postTextContent = postTextContent;
    }
}
