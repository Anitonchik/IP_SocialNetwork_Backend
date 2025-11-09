package com.example.SocialNetwork.entity;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class ChatUserId {
    private Long chatId;
    private Long userId;

    public ChatUserId() {
    }

    public ChatUserId(Long chatId, Long userId) {
        this.chatId = chatId;
        this.userId = userId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChatUserId other = (ChatUserId) obj;
        return Objects.equals(chatId, other.chatId) && Objects.equals(userId, other.userId);
    }
}
