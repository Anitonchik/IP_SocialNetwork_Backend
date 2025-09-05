package com.example.SocialNetwork.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private int userId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userAvatarURL;
    private String postImageURL;
    private String postTextContent;

    public PostDTO (int id, int userId, String postImageURL, String postTextContent) {
        this.id = id;
        this.userId = userId;
        this.postImageURL = postImageURL;
        this.postTextContent = postTextContent;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getUserAvatarURL() { return userAvatarURL; }
    public void setUserAvatarURL(String userAvatarURL) { this.userAvatarURL = userAvatarURL; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPostImageURL() { return postImageURL; }
    public void setPostImageURL(String postImageURL) { this.postImageURL = postImageURL; }
    public String getPostTextContent() { return postTextContent; }
    public void setPostTextContent(String postTextContent) { this.postTextContent = postTextContent; }
}
