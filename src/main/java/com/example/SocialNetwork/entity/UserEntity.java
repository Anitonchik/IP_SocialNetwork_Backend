package com.example.SocialNetwork.entity;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String userAvatarURL;
    @Column(nullable = false)
    private String userDescription;
    @Column(nullable = false, unique = true)
    private String pageAddress;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;

    @ManyToMany
    @JoinTable(
            name = "Subscriptions",
                joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "subscribed_user_id"),
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"user_id", "subscribed_user_id"})
            }
    )
    private List<UserEntity> followers;
    @ManyToMany
    @JoinTable(
            name = "Subscriptions",
            joinColumns = @JoinColumn(name = "subscribed_user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> subscriptions;

    public UserEntity(){super();}

    public UserEntity (String firstName, String lastName, String userName, String userAvatarURL,
                    String userDescription, String pageAddress, String phone, String password) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userAvatarURL = userAvatarURL;
        this.userDescription = userDescription;
        this.pageAddress = pageAddress;
        this.phone = phone;
        this.password = password;
        this.userRole = UserRole.USER;
        this.followers = new ArrayList<>();
        this.subscriptions = new ArrayList<>();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setPassword(UserRole userRole) {
        this.userRole = userRole;
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
