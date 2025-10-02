package com.example.SocialNetwork.api.Chat;

import java.util.Date;
import java.util.List;

public class ChatRq {
    private Date createdAt;
    private List<Long> participants;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Long> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Long> participants) {
        this.participants = participants;
    }

   /* public List<MessageRq> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageRq> messages) {
        this.messages = messages;
    }*/
}
