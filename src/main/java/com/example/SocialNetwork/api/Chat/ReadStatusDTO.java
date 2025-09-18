package com.example.SocialNetwork.api.Chat;

import java.util.Date;

public class ReadStatusDTO {
    private int userId;
    private Date readAt;

    public ReadStatusDTO() {
    }

    public ReadStatusDTO(int userId, Date readAt) {
        this.userId = userId;
        this.readAt = readAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getReadAt() {
        return readAt;
    }

    public void setReadAt(Date readAt) {
        this.readAt = readAt;
    }

}
