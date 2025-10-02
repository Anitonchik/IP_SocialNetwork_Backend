package com.example.SocialNetwork.api.user;

public class UserToUserDTO {
    private int id;
    private int userId;
    private int subscribedUserId;
    private UserDTO user;
    private UserDTO subscribedUser;

    public UserToUserDTO (int id, int userId, int subscribedUserId) {
        this.id = id;
        this.userId = userId;
        this.subscribedUserId = subscribedUserId;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSubscribedUserId() {
        return subscribedUserId;
    }

    public void setSubscribedUserId(int subscribedUserId) {
        this.subscribedUserId = subscribedUserId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public UserDTO getSubscribedUser() {
        return subscribedUser;
    }

    public void setSubscribedUser(UserDTO subscribedUser) {
        this.subscribedUser = subscribedUser;
    }
}
