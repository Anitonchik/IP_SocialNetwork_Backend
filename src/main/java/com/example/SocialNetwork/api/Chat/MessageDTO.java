package com.example.SocialNetwork.api.Chat;

import java.util.Date;
import java.util.List;

public class MessageDTO {
    private int id;
    private int chatId;
    private int userId;
    private String messageText;
    private Date createdAt;
    private List<String> attachments;
    private List<ReadStatusDTO> readBy;

    public MessageDTO() {
    }

    public MessageDTO(int id, int chatId, int userId, String messageText, Date createdAt,
                      List<String> attachments, List<ReadStatusDTO> readBy) {
        this.id = id;
        this.chatId = chatId;
        this.userId = userId;
        this.messageText = messageText;
        this.createdAt = createdAt;
        this.attachments = attachments;
        this.readBy = readBy;
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

    public List<ReadStatusDTO> getReadBy() {
        return readBy;
    }

    public void setReadBy(List<ReadStatusDTO> readBy) {
        this.readBy = readBy;
    }

}
