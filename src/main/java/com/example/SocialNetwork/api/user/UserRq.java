package com.example.SocialNetwork.api.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRq {
    @NotBlank
    @Size(min = 1, max = 50, message = "FirstName must not be longer than 50")
    private String firstName;
    @NotBlank
    @Size(min = 1, max = 50, message = "LastName must not be longer than 50")
    private String lastName;
    @NotBlank
    @Size(min = 1, max = 20, message = "UserName must not be longer than 20")
    private String userName;
    private String userAvatarURL;
    private String userDescription;
    private int publications;
    @Pattern(
            regexp = "^(\\+7|8)\\s?\\(?\\d{3}\\)?\\s?\\d{3}\\s?\\d{2}\\s?\\d{2}$",
            message = "Invalid phone number format. Expected format: +7 (XXX) XXX-XX-XX or 8 (XXX) XXX-XX-XX"
    )
    private String phone;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatarURL() {
        return userAvatarURL;
    }

    public void setUserAvatarURL(String userAvatarURL) {
        this.userAvatarURL = userAvatarURL;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPublications() {
        return publications;
    }

    public void setPublications(int publications) {
        this.publications = publications;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }
}
