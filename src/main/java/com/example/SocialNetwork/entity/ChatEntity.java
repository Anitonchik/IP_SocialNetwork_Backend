package com.example.SocialNetwork.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(
    name = "chats",
    uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"first_user_id", "second_user_id"},
                name = "id_chat_users_pair"
        )
    }
)
public class ChatEntity extends BaseEntity{
    @Column(nullable = false)
    private Date createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first_user_id", nullable = false)
    private UserEntity firstUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_user_id", nullable = false)
    private UserEntity secondUser;
    /*@OneToMany(mappedBy = "chat")
    @OrderBy("createdAt ASC")*/
    private String lastMessage;

    public ChatEntity() {super();}

    public ChatEntity(Date createdAt, UserEntity firstUser, UserEntity secondUser, String lastMessage) {
        this();
        this.createdAt = createdAt;
        this.firstUser = firstUser;
        this.secondUser = secondUser;
        this.lastMessage = lastMessage;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserEntity getFirstUser() { return firstUser; }

    public void setFirstUser(UserEntity firstUser) { this.firstUser = firstUser; }

    public UserEntity getSecondUser() { return secondUser; }

    public void setSecondUser(UserEntity secondUser) { this.secondUser = secondUser; }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    @PrePersist
    @PreUpdate
    public void normalizeUsers() {
        if (firstUser.id > secondUser.id) {
            UserEntity temp = firstUser;
            firstUser = secondUser;
            secondUser = temp;
        }
    }

}
