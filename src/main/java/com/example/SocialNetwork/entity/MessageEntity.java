package com.example.SocialNetwork.entity;

import jakarta.persistence.*;
//import org.h2.engine.User;

import java.util.Date;

@Entity
@Table(name = "messages")
public class MessageEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private ChatEntity chat;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @Column(name = "message_text", length = 4096, nullable = false)
    private String messageText;
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
    @Column(name = "is_edited", nullable = false)
    private Boolean isEdited;


    public MessageEntity() {
        super();
    }

    public MessageEntity(ChatEntity chat, UserEntity user, String messageText, Date createdAt) {
        this.chat = chat;
        this.user = user;
        this.messageText = messageText;
        this.createdAt = createdAt;
        this.isEdited = false;
    }

    public ChatEntity getChat() {
        return chat;
    }

    public void setChat(ChatEntity chat) {
        this.chat = chat;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

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
