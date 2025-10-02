package com.example.SocialNetwork.repository;

import com.example.SocialNetwork.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository extends MapRepository<UserEntity> {

    private Optional<UserEntity> createSubscribe(Long userId, Long subscribedUserId) {
        var user = super.findById(userId);
        var subscribedUser = super.findById(subscribedUserId);

        user.ifPresent(u -> subscribedUser.ifPresent(u::setSubscription));
        subscribedUser.ifPresent(su -> user.ifPresent(u -> u.setFollower(su)));

        user.ifPresent(super::save);
        subscribedUser.ifPresent(super::save);

        return subscribedUser;
    }
}
