package com.example.SocialNetwork.api.user;

import jakarta.validation.constraints.NotBlank;

public record UserToUserRq (@NotBlank Long userId, @NotBlank Long userFollowing) {

}
