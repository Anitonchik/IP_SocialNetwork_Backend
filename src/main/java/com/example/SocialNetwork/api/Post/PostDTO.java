package com.example.SocialNetwork.api.Post;

import com.example.SocialNetwork.api.User.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PostDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private UserDTO user;
    private String postImageURL;
    private String postTextContent;

    public PostDTO (int id, UserDTO user, String postImageURL, String postTextContent) {
        this.id = id;
        this.user = user;
        this.postImageURL = postImageURL;
        this.postTextContent = postTextContent;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPostImageURL() { return postImageURL; }
    public void setPostImageURL(String postImageURL) { this.postImageURL = postImageURL; }
    public String getPostTextContent() { return postTextContent; }
    public void setPostTextContent(String postTextContent) { this.postTextContent = postTextContent; }
    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }
}
