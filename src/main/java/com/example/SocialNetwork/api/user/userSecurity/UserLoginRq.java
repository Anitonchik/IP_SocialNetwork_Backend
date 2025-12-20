package com.example.SocialNetwork.api.user.userSecurity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginRq(
        @NotBlank @Size(min = 3) String userName,
        @NotBlank @Size(min = 3) String password) {
}