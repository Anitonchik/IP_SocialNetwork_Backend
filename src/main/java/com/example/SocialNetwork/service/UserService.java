package com.example.SocialNetwork.service;

import com.example.SocialNetwork.api.PageRs;
import com.example.SocialNetwork.api.post.PostRs;
import com.example.SocialNetwork.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import com.example.SocialNetwork.error.NotFoundException;
import com.example.SocialNetwork.api.user.UserRq;
import com.example.SocialNetwork.api.user.UserRs;
import com.example.SocialNetwork.entity.Report;
import com.example.SocialNetwork.entity.UserEntity;
import com.example.SocialNetwork.error.AlreadyExistsException;
import com.example.SocialNetwork.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    final private UserRepository repository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public void register(UserRq dto) {
        UserEntity entity = new UserEntity(dto.firstName(), dto.lastName(),
                dto.userName(), dto.userAvatarURL(), dto.userDescription(),
                "http://" + dto.userName(), dto.phone(), passwordEncoder.encode(dto.password()));

        repository.save(entity);
    }

    @Transactional(readOnly = true)
    public PageRs<UserRs> getAll(Pageable pageable, Long userAuthId) {
        return PageRs.from(repository.findAll(pageable), userAuthId, this);
    }

    @Transactional(readOnly = true)
    public PageRs<UserRs> getAllNotByAuthUser(Pageable pageable, Long userId) {
        return PageRs.from(repository.findByIdNot(pageable, userId), userId, this);
    }

    @Transactional(readOnly = true)
    public PageRs<UserRs> getFilterNotByAuthUser(Pageable pageable, String usernamePart, Long userAuthId) {
        return PageRs.from(repository.findByUserNameContainingIgnoreCaseAndIdNot(usernamePart, userAuthId, pageable), userAuthId, this);
    }

    @Transactional(readOnly = true)
    public PageRs<UserRs> getFilterSortNotByAuthUser(Pageable pageable, Long userAuthId) {
        return PageRs.from(repository.findByIdNotOrderByUserNameAsc(userAuthId, pageable), userAuthId, this);
    }


    @Transactional(readOnly = true)
    public PageRs<UserRs> getFilterSortFilterNotByAuthUser(Pageable pageable, String usernamePart, Long userAuthId) {
        return PageRs.from(repository.findByUserNameContainingIgnoreCaseAndIdNotOrderByUserNameAsc(usernamePart, userAuthId, pageable), userAuthId, this);
    }

    @Transactional(readOnly = true)
    public PageRs<UserRs> getFollowers(Pageable pageable, Long userId) {
            return PageRs.from(repository.findFollowersByUserId(pageable, userId), userId, this);
    }


    @Transactional(readOnly = true)
    public PageRs<UserRs> getSubscriptions(Pageable pageable, Long userId) {
        return PageRs.from(repository.findSubscriptionsByUserId(pageable, userId), userId, this);
    }

    @Transactional(readOnly = true)
    public Boolean getSubscription(Long id, Long subscribedUserId) {
        var subscriptions = getEntity(subscribedUserId).getSubscriptions();
        /*return subscriptions.stream()
                .anyMatch(subscribedUser -> Objects.equals(subscribedUser.getId(), subscribedUserId));*/

        for (var user : subscriptions) {
            var flag = Objects.equals(user.getId(), id);
            if (flag)
                return true;
        }
        return false;
    }

    public List<UserEntity> getSubscriptions(Long userAuthId) {
        return getEntity(userAuthId).getSubscriptions();
    }

    public List<UserEntity> getFollowers(Long userAuthId) {
        return getEntity(userAuthId).getFollowers();
    }

    public PageRs<UserRs> searchUsers(String searchQuery, Pageable pageable, Long userId) {
        var page = repository.findByUserNameContainingIgnoreCase(searchQuery, pageable);
        return PageRs.from(repository.findByUserNameContainingIgnoreCase(searchQuery, pageable), userId, this);

    }

    @Transactional(readOnly = true)
    public UserRs get(Long id) {
        final UserEntity entity = getEntity(id);
        return UserRs.from(entity, false);
    }

    @Transactional(readOnly = true)
    public UserEntity getByUserName(String userName) {
        final UserEntity entity = repository.findByUserName(userName).get();
        return entity;
    }

    @Transactional
    public UserRs create(UserRq dto) {
        checkUserNameAndPhone(dto.userName(), dto.phone());
        UserEntity entity = new UserEntity(dto.firstName(), dto.lastName(),
                dto.userName(), dto.userAvatarURL(), dto.userDescription(),
                "http://" + dto.userName(), dto.phone(), passwordEncoder.encode(dto.password()));
        entity = repository.save(entity);
        return UserRs.from(entity, false);
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
        entity.setPassword(dto.password());
        entity = repository.save(entity);
        return UserRs.from(entity, false);
    }

    @Transactional
    public UserRs createSubscription(Long id, Long userFollowingId) {
        UserEntity user = getEntity(id);
        UserEntity userFollowing = getEntity(userFollowingId);
        user.setSubscription(userFollowing);
        user = repository.save(user);
        return UserRs.from(user, true);
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
        return UserRs.fromList(user.getSubscriptions(), id, this);
    }

    @Transactional
    public UserRs delete(Long id) {
        final UserEntity entity = getEntity(id);
        chatService.deleteChatsByUserId(id);
        postRepository.deleteAllByUser_Id(entity.getId());
        repository.delete(entity);
        return UserRs.from(entity, false);
    }

    @Transactional(readOnly = true)
    public List<Report> getStatistic(Long id) {
        return repository.getUserStatistics(id);
    }

}
