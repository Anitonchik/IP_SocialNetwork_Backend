package com.example.SocialNetwork.entity;

import jakarta.persistence.*;

import java.util.Date;

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
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
    @Column(name = "is_edited", nullable = false)
    private Boolean isEdited;

    public PostEntity(){super();}

    public PostEntity (UserEntity user, String postImageURL, String postTextContent, Date createdAt) {
        this();
        this.user = user;
        this.postImageURL = postImageURL;
        this.postTextContent = postTextContent;
        this.createdAt = createdAt;
        this.isEdited = false;
    }
    public String getPostImageURL() { return postImageURL; }
    public void setPostImageURL(String postImageURL) { this.postImageURL = postImageURL; }
    public String getPostTextContent() { return postTextContent; }
    public void setPostTextContent(String postTextContent) { this.postTextContent = postTextContent; }
    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }
    public Boolean getIsEdited() {
        return isEdited;
    }

    public void setIsEdited(Boolean isEdited) {
        this.isEdited = isEdited;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
