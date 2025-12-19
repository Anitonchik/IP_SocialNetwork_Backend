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
    @ManyToOne
    @JoinColumn(name = "first_user_id", nullable = false)
    private UserEntity firstUser;

    @ManyToOne
    @JoinColumn(name = "second_user_id", nullable = false)
    private UserEntity secondUser;

    public ChatEntity() {super();}

    public ChatEntity(UserEntity firstUser, UserEntity secondUser) {
        this();
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }

    public UserEntity getFirstUser() { return firstUser; }

    public void setFirstUser(UserEntity firstUser) { this.firstUser = firstUser; }

    public UserEntity getSecondUser() { return secondUser; }

    public void setSecondUser(UserEntity secondUser) { this.secondUser = secondUser; }

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
