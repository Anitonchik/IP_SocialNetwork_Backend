package com.example.SocialNetwork.api.Chat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

public class ChatRq {
    @NotNull
    private Date createdAt;
    @NotEmpty
    @Size(min = 2, max = 2, message = "There must be 2 chat participants")
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
