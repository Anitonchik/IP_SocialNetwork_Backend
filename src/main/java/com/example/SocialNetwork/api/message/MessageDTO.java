package com.example.SocialNetwork.api.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class MessageDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private int chatId;
    private String messageText;
    private Date createdAt;
    private Boolean isEdited;

    public MessageDTO() {
    }

    public MessageDTO(int id, int chatId, String messageText, Date createdAt) {
        this.id = id;
        this.chatId = chatId;
        this.messageText = messageText;
        this.createdAt = createdAt;
        this.isEdited = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
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
