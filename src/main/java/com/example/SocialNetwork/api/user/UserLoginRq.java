package com.example.SocialNetwork.api.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginRq(
        @NotBlank @Size(min = 3) String username,
        @NotBlank @Size(min = 3) String password) {
}