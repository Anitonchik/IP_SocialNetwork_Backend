package com.example.SocialNetwork.api.Chat;

import com.example.SocialNetwork.api.message.MessageDTO;
import com.example.SocialNetwork.api.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class ChatDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private UserDTO chatUser;
    private Date createdAt;
    private List<Integer> participants;
    private List<MessageDTO> messages;

    public ChatDTO() {
    }

    public ChatDTO(int id, Date createdAt, List<Integer> participants) {
        this.id = id;
        this.createdAt = createdAt;
        this.participants = participants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return chatUser;
    }

    public void setUser(UserDTO chatUser) {
        this.chatUser = chatUser;
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
