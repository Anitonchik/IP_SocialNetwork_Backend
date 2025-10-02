package com.example.SocialNetwork.entity;

public class PostEntity extends BaseEntity{
    private UserEntity user;
    private String postImageURL;
    private String postTextContent;

    public PostEntity (UserEntity user, String postImageURL, String postTextContent) {
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
