package com.example.SocialNetwork.api.Chat;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ChatRq (@NotNull Date createdAt, Long firstUserId, Long secondUserId) {

}
