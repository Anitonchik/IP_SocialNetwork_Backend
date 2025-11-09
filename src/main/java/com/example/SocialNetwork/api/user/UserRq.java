package com.example.SocialNetwork.api.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRq (@NotBlank @Size(min = 1, max = 50, message = "FirstName must not be longer than 50") String firstName,
                      @NotBlank @Size(min = 1, max = 50, message = "LastName must not be longer than 50") String lastName,
                      @NotBlank @Size(min = 1, max = 20, message = "UserName must not be longer than 20") String userName,
                      String userAvatarURL, String userDescription, int publications,
                      @Pattern(
                              regexp = "^(\\+7|8)\\s?\\(?\\d{3}\\)?\\s?\\d{3}\\s?\\d{2}\\s?\\d{2}$",
                              message = "Invalid phone number format. Expected format: +7 (XXX) XXX-XX-XX or 8 (XXX) XXX-XX-XX"
                      ) String phone) {
}
