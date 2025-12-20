package com.example.SocialNetwork.api.user.userSecurity;

import com.example.SocialNetwork.validation.password.PasswordMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@PasswordMatch(first = "newPassword", second = "newPasswordConfirm")
public record UserUpdateRq(
        @NotBlank @Size(min = 3) String oldPassword,
        @NotBlank @Size(min = 3) String newPassword,
        @NotBlank @Size(min = 3) String newPasswordConfirm) {
}