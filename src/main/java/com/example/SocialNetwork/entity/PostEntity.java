package com.example.SocialNetwork.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class PostEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @Column(name = "post_image_url", nullable = false)
    private String postImageURL;
    @Column(name = "post_text_content", nullable = false)
    private String postTextContent;

    public PostEntity(){super();}

    public PostEntity (UserEntity user, String postImageURL, String postTextContent) {
        this();
        this.user = user;
        this.postImageURL = postImageURL;
        this.postTextContent = postTextContent;
    }
    public String getPostImageURL() { return postImageURL; }
    public void setPostImageURL(String postImageURL) { this.postImageURL = postImageURL; }
    public String getPostTextContent() { return postTextContent; }
    public void setPostTextContent(String postTextContent) { this.postTextContent = postTextContent; }
    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }
}
