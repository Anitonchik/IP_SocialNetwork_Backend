package com.example.SocialNetwork.api.Chat;

import com.example.SocialNetwork.api.message.MessageRq;
import com.example.SocialNetwork.api.user.UserRq;

import java.util.Date;
import java.util.List;

public class ChatRq {
    private Date createdAt;
    private List<UserRq> participants;
    private List<MessageRq> messages;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<UserRq> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserRq> participants) {
        this.participants = participants;
    }

    public List<MessageRq> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageRq> messages) {
        this.messages = messages;
    }
}
