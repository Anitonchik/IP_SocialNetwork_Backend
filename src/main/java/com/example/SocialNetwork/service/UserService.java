package com.example.SocialNetwork.service;

import com.example.SocialNetwork.api.PageRs;
import com.example.SocialNetwork.api.post.PostRs;
import org.springframework.data.domain.Pageable;
import com.example.SocialNetwork.error.NotFoundException;
import com.example.SocialNetwork.api.user.UserRq;
import com.example.SocialNetwork.api.user.UserRs;
import com.example.SocialNetwork.entity.Report;
import com.example.SocialNetwork.entity.UserEntity;
import com.example.SocialNetwork.error.AlreadyExistsException;
import com.example.SocialNetwork.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    //@Transactional(propagation = Propagation.MANDATORY)
    @Transactional(readOnly = true)
    public UserEntity getEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(UserEntity.class, id));
    }

    @Transactional(readOnly = true)
    public PageRs<UserRs> getAll(Pageable pageable) {
        return PageRs.from(repository.findAll(pageable), UserRs::from);
    }

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

    public PageRs<UserRs> searchUsers(String searchQuery, Pageable pageable) {
        var page = repository.findByUserNameContainingIgnoreCase(searchQuery, pageable);
        return PageRs.from(repository.findByUserNameContainingIgnoreCase(searchQuery, pageable), UserRs::from);

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
                "http://" + dto.userName(), dto.phone(), dto.password());
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
    public UserRs createSubscription(Long id, Long userFollowingId) {
        UserEntity user = getEntity(id);
        UserEntity userFollowing = getEntity(userFollowingId);
        user.setSubscription(userFollowing);
        user = repository.save(user);
        return UserRs.from(user);
    }

    //userFollowing - на кого я подписана
    @Transactional
    public List<UserRs> deleteSubscription(Long id, Long userFollowingId) {
        UserEntity user = getEntity(id);
        UserEntity subscribedUser = getEntity(userFollowingId);
        var sbc = user.getSubscriptions();
        sbc.removeIf(sub -> Objects.equals(sub.getId(), userFollowingId));
        user.setSubscriptions(sbc);
        user = repository.save(user);
        return UserRs.fromList(user.getSubscriptions());
    }

    @Transactional
    public UserRs delete(Long id) {
        final UserEntity entity = getEntity(id);
        repository.delete(entity);
        return UserRs.from(entity);
    }

    @Transactional(readOnly = true)
    public List<Report> getStatistic(Long id) {
        return repository.getUserStatistics(id);
    }

}
