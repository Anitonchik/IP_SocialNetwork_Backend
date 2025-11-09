package com.example.SocialNetwork.api.Chat;

import com.example.SocialNetwork.api.user.UserRq;
import com.example.SocialNetwork.api.user.UserRs;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

public record ChatRq (@NotNull Date createdAt, Long firstUserId, Long secondUserId) {

}
