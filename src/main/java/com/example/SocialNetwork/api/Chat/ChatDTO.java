package com.example.SocialNetwork.api.Chat;

import com.example.SocialNetwork.chatType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class ChatDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String chatName;
    private String chatAvatar;
    private chatType chatType;
    private Date createdAt;
    private List<Integer> participants;
    private List<MessageDTO> messages;

    public ChatDTO() {
    }

    public ChatDTO(int id, String chatName, String chatAvatar, chatType chatType,
                   Date createdAt, List<Integer> participants) {
        this.id = id;
        this.chatName = chatName;
        this.chatAvatar = chatAvatar;
        this.chatType = chatType;
        this.createdAt = createdAt;
        this.participants = participants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getChatAvatar() {
        return chatAvatar;
    }

    public void setChatAvatar(String chatAvatar) {
        this.chatAvatar = chatAvatar;
    }

    public chatType getChatType() {
        return chatType;
    }

    public void setChatType(chatType chatType) {
        this.chatType = chatType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Integer> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Integer> participants) {
        this.participants = participants;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }

}
