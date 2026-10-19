package com.example.SocialNetwork.api.user;

import com.example.SocialNetwork.entity.UserEntity;
import com.example.SocialNetwork.entity.UserRole;
import com.example.SocialNetwork.service.UserService;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import jakarta.validation.constraints.NotBlank;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

public record UserRs (Long id, @NotBlank String firstName, @NotBlank String lastName, @NotBlank String userName,
                      String userAvatarURL, String userDescription, String pageAddress, String phone, UserRole userRole,
                      Boolean isSubscribed) {

    public static UserRs from(UserEntity entity, boolean isSubscribed) {
        return new UserRs(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getUserName(),
                entity.getUserAvatarURL(), entity.getUserDescription(), entity.getPageAddress(), entity.getPhone(),
                entity.getUserRole(), isSubscribed);
    }

    public static List<UserRs> fromList(Iterable<UserEntity> entities, Long userAuthId, UserService userService) {
        var subscriptions = userService.getFollowers(userAuthId);
        /*var usersRs = StreamSupport.stream(entities.spliterator(), false)
                .map(user -> from(user, userAuthId, userService))
                .toList();*/

        List<UserRs> usersRs = new ArrayList<>();
        for(var user : entities) {
            var isSubscribed = false;
            for(var subscribedUser : subscriptions) {
                if (Objects.equals(subscribedUser.getId(), user.getId())) {
                    isSubscribed = true;
                }
            }
            usersRs.add(from(user, isSubscribed));
        }
        return usersRs;
    }
}
