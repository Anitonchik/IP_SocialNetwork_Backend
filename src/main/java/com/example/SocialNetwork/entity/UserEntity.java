package com.example.SocialNetwork.entity;

import java.util.List;

public class UserEntity extends BaseEntity{
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String userAvatarURL;
    private String userDescription;
    private String pageAddress;
    private int publications;
    private List<UserToUserEntity> followers;
    private List<UserToUserEntity> subscriptions;
    private String phone;

    public UserEntity(){super();}

    public UserEntity (Long id, String firstName, String lastName, String userName, String userAvatarURL,
                    String userDescription, String pageAddress, String phone) {
        this();
        this.id = id;
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

    public List<UserToUserEntity> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserToUserEntity> followers) {
        this.followers = followers;
    }

    public List<UserToUserEntity> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<UserToUserEntity> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }
}
