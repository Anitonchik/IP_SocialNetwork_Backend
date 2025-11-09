package com.example.SocialNetwork.entity;

import com.example.SocialNetwork.api.user.UserDTO;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "chats")
public class ChatEntity extends BaseEntity{
    @Column(nullable = false)
    private Date createdAt;
    @OneToMany(mappedBy = "users")
    private List<UserEntity> participants;
    @OneToMany(mappedBy = "messages")
    @OrderBy("createdAt ASC")
    private List<MessageEntity> messages;

    public ChatEntity() {super();}

    public ChatEntity(Date createdAt, List<UserEntity> participants, List<MessageEntity> messages) {
        this();
        this.createdAt = createdAt;
        this.participants = participants;
        this.messages = messages;
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
