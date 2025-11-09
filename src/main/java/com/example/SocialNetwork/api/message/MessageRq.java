package com.example.SocialNetwork.api.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record MessageRq (@NotNull Long chatId, @NotNull Long userId, @NotBlank String messageText, @NotNull Date createdAt) {

}
