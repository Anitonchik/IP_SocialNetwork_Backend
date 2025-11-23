package com.example.SocialNetwork.entity;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.Date;

public class Report {
    int date;
    Long chatCount;

    public Report() {}

    public Report(int date, Long chatCount) {
        this.date = date;
        this.chatCount = chatCount;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Long getChatCount() {
        return chatCount;
    }

    public void setChatCount(Long chatCount) {
        this.chatCount = chatCount;
    }
}
