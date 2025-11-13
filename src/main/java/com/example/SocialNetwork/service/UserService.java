package com.example.SocialNetwork.service;

import com.example.SocialNetwork.api.NotFoundException;
import com.example.SocialNetwork.api.user.UserRq;
import com.example.SocialNetwork.api.user.UserRs;
import com.example.SocialNetwork.entity.UserEntity;
import com.example.SocialNetwork.error.AlreadyExistsException;
import com.example.SocialNetwork.repository.UserRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    final private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    private void checkUserNameAndPhone(String name, String phone) {
        repository.findByUserName(name).ifPresent(val -> {
            throw new AlreadyExistsException(UserEntity.class, name);
        });
        repository.findByPhone(phone).ifPresent(val -> {
            throw new AlreadyExistsException(UserEntity.class, name);
        });
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public UserEntity getEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(UserEntity.class, id));
    }

    @Transactional(readOnly = true)
    public List<UserRs> getAll() {return UserRs.fromList(repository.findAll());}

    @Transactional(readOnly = true)
    public List<UserRs> getFollowers(Long id) {
        return UserRs.fromList(getEntity(id).getFollowers());
    }

    @Transactional(readOnly = true)
    public List<UserRs> getSubscriptions(Long id) {
        return UserRs.fromList(getEntity(id).getSubscriptions());
    }

    @Transactional(readOnly = true)
    public Boolean getSubscription(Long id, Long subscribedUserId) {
        var subscriptions = getEntity(id).getSubscriptions();
        return subscriptions.stream()
                .anyMatch(subscribedUser -> Objects.equals(subscribedUser.getId(), subscribedUserId));
    }

    @Transactional(readOnly = true)
    public UserRs get(Long id) {
        final UserEntity entity = getEntity(id);
        return UserRs.from(entity);
    }

    @Transactional
    public UserRs create(UserRq dto) {
        checkUserNameAndPhone(dto.userName(), dto.phone());
        UserEntity entity = new UserEntity(dto.firstName(), dto.lastName(),
                dto.userName(), dto.userAvatarURL(), dto.userDescription(),
                "http://" + dto.userName(), dto.phone());
        entity = repository.save(entity);
        return UserRs.from(entity);
    }

    @Transactional
    public UserRs update(Long id, UserRq dto) {
        checkUserNameAndPhone(dto.userName(), dto.phone());
        UserEntity entity = getEntity(id);
        entity.setFirstName(dto.firstName());
        entity.setLastName(dto.lastName());
        entity.setUserName(dto.userName());
        entity.setUserAvatarURL(dto.userAvatarURL());
        entity.setUserDescription(dto.userDescription());
        entity.setPhone(dto.phone());
        entity = repository.save(entity);
        return UserRs.from(entity);
    }

    @Transactional
    public UserRs createSubscription(Long id, Long subscribedUserId) {
        UserEntity user = getEntity(id);
        UserEntity subscribedUser = getEntity(subscribedUserId);
        user.setSubscription(subscribedUser);
        user = repository.save(user);
        return UserRs.from(user);
    }

    @Transactional
    public UserRs deleteSubscription(Long id, Long subscribedUserId) {
        UserEntity user = getEntity(id);
        UserEntity subscribedUser = getEntity(subscribedUserId);
        var sbc = user.getSubscriptions();
        sbc.stream().
        user.setSubscription(subscribedUser);
        user = repository.save(user);
        return UserRs.from(user);
    }

    @Transactional
    public UserRs delete(Long id) {
        final UserEntity entity = getEntity(id);
        repository.delete(entity);
        return UserRs.from(entity);
    }

}
