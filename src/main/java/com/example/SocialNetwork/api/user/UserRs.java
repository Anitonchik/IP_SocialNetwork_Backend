package com.example.SocialNetwork.api.user;

import com.example.SocialNetwork.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.stream.StreamSupport;

public record UserRs (Long id, @NotBlank String firstName, @NotBlank String lastName, @NotBlank String userName,
                      String userAvatarURL, String userDescription, String pageAddress, String phone) {
    public static UserRs from(UserEntity entity) {
        return new UserRs(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getUserName(),
                entity.getUserAvatarURL(), entity.getUserDescription(), entity.getPageAddress(), entity.getPhone());
    }

    public static List<UserRs> fromList(Iterable<UserEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(UserRs::from)
                .toList();
    }
}
