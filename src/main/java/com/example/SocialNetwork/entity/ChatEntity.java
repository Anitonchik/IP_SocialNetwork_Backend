package com.example.SocialNetwork.entity;

import com.example.SocialNetwork.api.user.UserDTO;

import java.util.Date;
import java.util.List;

public class ChatEntity extends BaseEntity{
    private Long id;
    private UserDTO chatUser;
    private Date createdAt;
    private List<UserEntity> participants;
    private List<MessageEntity> messages;

    public ChatEntity() {super();}

    public ChatEntity(Long id, Date createdAt, List<UserEntity> participants) {
        this();
        this.id = id;
        this.createdAt = createdAt;
        this.participants = participants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return chatUser;
    }

    public void setUser(UserDTO chatUser) {
        this.chatUser = chatUser;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<UserEntity> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserEntity> participants) {
        this.participants = participants;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }
}
