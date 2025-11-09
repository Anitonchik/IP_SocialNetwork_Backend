package com.example.SocialNetwork.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "chat_user")
public class ChatUserEntity {
    @EmbeddedId
    private ChatUserId id = new ChatUserId();

    @ManyToOne
    @MapsId("chatId")
    @JoinColumn(name = "chat_id", nullable = false)
    private ChatEntity chat;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public ChatUserEntity() {
    }

    public ChatUserEntity(ChatEntity chat, UserEntity user) {
        this.chat = chat;
        this.user = user;
        this.id = new ChatUserId(chat.getId(), user.getId());
    }

    public ChatUserId getId() {
        return id;
    }

    public void setId(ChatUserId id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChatUserEntity other = (ChatUserEntity) obj;
        return Objects.equals(id, other.id);
    }

}
