package com.example.SocialNetwork.api.Post;

import com.example.SocialNetwork.api.user.UserRq;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PostRq (@NotNull Long userId, String postImageURL,
                     @NotEmpty @Size(min = 1, max = 1000,
                             message = "Text content must not be longer than 1000") String postTextContent) {

}
