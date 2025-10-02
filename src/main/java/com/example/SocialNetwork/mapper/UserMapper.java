package com.example.SocialNetwork.mapper;

import com.example.SocialNetwork.api.user.UserRq;
import com.example.SocialNetwork.api.user.UserRs;
import com.example.SocialNetwork.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class UserMapper {
    public UserRq toRqDto(String firstName, String lastName, String userName, String userAvatarURL,
                          String description, String phone) {
        final UserRq dto = new UserRq();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setUserName(userName);
        dto.setUserAvatarURL(userAvatarURL);
        dto.setUserDescription(description);
        dto.setPhone(phone);
        return dto;
    }

    public UserRs toRsDto(UserEntity userEntity) {
        final UserRs dto = new UserRs();
        dto.setId(userEntity.getId());
        dto.setFirstName(userEntity.getFirstName());
        dto.setLastName(userEntity.getLastName());
        dto.setUserName(userEntity.getUserName());
        dto.setUserAvatarURL(userEntity.getUserAvatarURL());
        dto.setUserDescription(userEntity.getUserDescription());
        dto.setPhone(userEntity.getPhone());
        return dto;
    }

    public List<UserRs> toRsListDto(Iterable<UserEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::toRsDto)
                .toList();
    }
}
