package com.example.SocialNetwork.api.user.userSecurity;


import com.example.SocialNetwork.entity.UserEntity;
import com.example.SocialNetwork.entity.UserRole;

import java.util.List;
import java.util.stream.StreamSupport;

public record UserSecurityRs(
        String userName,
        String role) {

    public static UserSecurityRs from(UserEntity entity) {
        return new UserSecurityRs(
                entity.getUserName(),
                UserRole.USER.toString());
    }

    public static List<UserSecurityRs> fromList(Iterable<UserEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(UserSecurityRs::from)
                .toList();
    }
}
