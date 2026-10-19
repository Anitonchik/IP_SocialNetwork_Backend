package com.example.SocialNetwork.api.post;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record PostRq (@NotNull Long userId, String postImageURL,
                     @NotEmpty @Size(min = 1, max = 1000,
                             message = "Text content must not be longer than 1000") String postTextContent,
                      @NotNull Date createdAt) {

}
