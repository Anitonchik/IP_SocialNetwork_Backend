package com.example.SocialNetwork.api.Chat;

import com.example.SocialNetwork.api.message.MessageRs;
import com.example.SocialNetwork.api.user.UserRs;

import java.util.Date;
import java.util.List;

public class ChatRs {
    private Long id;
    private Date createdAt;
    private List<UserRs> participants;
    private List<MessageRs> messages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<UserRs> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserRs> participants) {
        this.participants = participants;
    }

    public List<MessageRs> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageRs> messages) {
        this.messages = messages;
    }
}
