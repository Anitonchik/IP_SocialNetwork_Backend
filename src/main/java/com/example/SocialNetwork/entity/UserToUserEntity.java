package com.example.SocialNetwork.entity;

public class UserToUserEntity extends BaseEntity{
    private Long id;
    private int userId;
    private int subscribedUserId;
    private UserEntity user;
    private UserEntity subscribedUser;

    public UserToUserEntity(){super();}

    public UserToUserEntity (Long id, int userId, int subscribedUserId) {
        this();
        this.id = id;
        this.userId = userId;
        this.subscribedUserId = subscribedUserId;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getSubscribedUser() {
        return subscribedUser;
    }

    public void setSubscribedUser(UserEntity subscribedUser) {
        this.subscribedUser = subscribedUser;
    }
}
