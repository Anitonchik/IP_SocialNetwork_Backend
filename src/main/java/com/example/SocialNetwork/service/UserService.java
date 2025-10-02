package com.example.SocialNetwork.service;

import com.example.SocialNetwork.api.NotFoundException;
import com.example.SocialNetwork.api.user.UserRq;
import com.example.SocialNetwork.api.user.UserRs;
import com.example.SocialNetwork.entity.UserEntity;
import com.example.SocialNetwork.mapper.UserMapper;
import com.example.SocialNetwork.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    final private UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.mapper = userMapper;
    }

    public UserEntity getEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(UserEntity.class, id));
    }

    public List<UserRs> getAll() {
        return mapper.toRsListDto(repository.findAll());
    }

    public UserRs get(Long id) {
        final UserEntity entity = getEntity(id);
        return mapper.toRsDto(entity);
    }

    public UserRs create(UserRq dto) {
        UserEntity entity = new UserEntity(dto.getFirstName(), dto.getLastName(),
                dto.getUserName(), dto.getUserAvatarURL(), dto.getUserDescription(),
                "http://" + dto.getUserName(), dto.getPhone());
        entity = repository.save(entity);
        return mapper.toRsDto(entity);
    }

    public UserRs update(Long id, UserRq dto) {
        UserEntity entity = getEntity(id);
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setUserName(dto.getUserName());
        entity.setUserAvatarURL(dto.getUserAvatarURL());
        entity.setUserDescription(dto.getUserDescription());
        entity.setPhone(dto.getPhone());
        entity = repository.save(entity);
        return mapper.toRsDto(entity);
    }

    public UserRs delete(Long id) {
        final UserEntity entity = getEntity(id);
        repository.delete(entity);
        return mapper.toRsDto(entity);
    }


}
