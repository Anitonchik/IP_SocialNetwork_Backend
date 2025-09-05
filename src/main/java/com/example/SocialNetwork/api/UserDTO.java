package com.example.SocialNetwork.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("user_name")
    private String userName;
    private String userAvatarURL;
    private String userDescription;
    private String pageAddress;
    private int publications;
    private int followers;
    private int subscriptions;
    private String phone;

    public UserDTO (int id, String firstName, String lastName, String userName, String userAvatarURL,
                    String userDescription, String pageAddress, int publications,
                    int followers, int subscriptions,
                    String phone, List<Integer> postIds) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userAvatarURL = userAvatarURL;
        this.userDescription = userDescription;
        this.pageAddress = pageAddress;
        this.publications = publications;
        this. followers = followers;
        this.subscriptions = subscriptions;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getPageAddress() {
        return pageAddress;
    }

    public void setPageAddress(String pageAddress) {
        this.pageAddress = pageAddress;
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

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(int subscriptions) {
        this.subscriptions = subscriptions;
    }



}
