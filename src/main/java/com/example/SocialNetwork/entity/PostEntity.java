package com.example.SocialNetwork.entity;

public class PostEntity extends BaseEntity{
    private Long id;
    private UserEntity user;
    private String postImageURL;
    private String postTextContent;

    public PostEntity (Long id, UserEntity user, String postImageURL, String postTextContent) {
        this.id = id;
        this.user = user;
        this.postImageURL = postImageURL;
        this.postTextContent = postTextContent;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPostImageURL() { return postImageURL; }
    public void setPostImageURL(String postImageURL) { this.postImageURL = postImageURL; }
    public String getPostTextContent() { return postTextContent; }
    public void setPostTextContent(String postTextContent) { this.postTextContent = postTextContent; }
    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }
}
