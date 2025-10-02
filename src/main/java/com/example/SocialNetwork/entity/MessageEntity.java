package com.example.SocialNetwork.entity;

import java.util.Date;
import java.util.List;

public class MessageEntity extends BaseEntity{
    private Long chatId;
    private Long userId;
    private String messageText;
    private Date createdAt;
    private Boolean isEdited;

    public MessageEntity() {super();}

    public MessageEntity(Long chatId, Long userId, String messageText, Date createdAt) {
        this.chatId = chatId;
        this.userId = userId;
        this.messageText = messageText;
        this.createdAt = createdAt;
        this.isEdited = false;
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
