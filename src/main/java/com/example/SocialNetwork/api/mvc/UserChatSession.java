package com.example.SocialNetwork.api.mvc;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)

public class UserChatSession implements Serializable {
    private String lastChatUserName;
    private LocalDateTime lastChatVisitTime;
    private boolean hasLastChat = false;

    public void setLastChat(String userName) {
        this.lastChatUserName = userName;
        this.lastChatVisitTime = LocalDateTime.now();
        this.hasLastChat = true;
    }

    public String getLastChatUserName() {
        return lastChatUserName;
    }

    public LocalDateTime getLastChatVisitTime() {
        return lastChatVisitTime;
    }

    public boolean isHasLastChat() {
        return hasLastChat;
    }
}
