package com.example.SocialNetwork.entity;

import java.util.List;

public class UserEntity extends BaseEntity{
    private String firstName;
    private String lastName;
    private String userName;
    private String userAvatarURL;
    private String userDescription;
    private String pageAddress;
    private int publications;
    private List<UserEntity> followers;
    private List<UserEntity> subscriptions;
    private String phone;

    public UserEntity(){super();}

    public UserEntity (String firstName, String lastName, String userName, String userAvatarURL,
                    String userDescription, String pageAddress, String phone) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userAvatarURL = userAvatarURL;
        this.userDescription = userDescription;
        this.pageAddress = pageAddress;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<UserEntity> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserEntity> followers) {
        this.followers = followers;
    }

    public List<UserEntity> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<UserEntity> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }



    public void setFollower(UserEntity follower) {
        this.followers.add(follower);
    }

    public void setSubscription(UserEntity subscription) {
        this.subscriptions.add(subscription);
    }
}
