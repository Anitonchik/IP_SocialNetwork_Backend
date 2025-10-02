package com.example.SocialNetwork.entity;

import java.util.Date;
import java.util.List;

public class MessageEntity extends BaseEntity{
    private Long id;
    private int chatId;
    private int userId;
    private String messageText;
    private Date createdAt;
    private Boolean isEdited;
    private List<String> attachments;

    public MessageEntity() {super();}

    public MessageEntity(Long id, int chatId, int userId, String messageText, Date createdAt,
                      List<String> attachments) {
        this.id = id;
        this.chatId = chatId;
        this.userId = userId;
        this.messageText = messageText;
        this.createdAt = createdAt;
        this.attachments = attachments;
        this.isEdited = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

}
