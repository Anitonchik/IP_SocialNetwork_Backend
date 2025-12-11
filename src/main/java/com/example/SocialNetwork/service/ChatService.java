package com.example.SocialNetwork.service;

import com.example.SocialNetwork.api.chat.ChatRq;
import com.example.SocialNetwork.api.chat.ChatRs;
import com.example.SocialNetwork.api.PageRs;
import com.example.SocialNetwork.error.NotFoundException;
import com.example.SocialNetwork.entity.ChatEntity;
import com.example.SocialNetwork.entity.UserEntity;
import com.example.SocialNetwork.error.AlreadyExistsException;
import com.example.SocialNetwork.repository.ChatRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatService {
    private final ChatRepository repository;
    private final UserService userService;


    public ChatService(ChatRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    private void checkUsers(Long firstUserId, Long secondUserId) {
        repository.findChatByUsers(firstUserId, secondUserId).ifPresent(val -> {
            throw new AlreadyExistsException(ChatEntity.class, firstUserId, secondUserId);
        });
    }

    //@Transactional(propagation = Propagation.MANDATORY)
    @Transactional(readOnly = true)
    public ChatEntity getEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ChatEntity.class, id));
    }

    @Transactional(readOnly = true)
    public PageRs<ChatRs> getAll(Pageable pageable, long userId) {
        return PageRs.from(repository.findAll(pageable), userId);
    }

    @Transactional(readOnly = true)
    public ChatRs get(Long id, Long userId) {
        final ChatEntity entity = getEntity(id);
        return ChatRs.from(entity, userId);
    }

    @Transactional(readOnly = true)
    public List<ChatRs> getByUser(Long userId) {
        var r = repository.findUsersChats(userId);
        return ChatRs.fromList(repository.findUsersChats(userId), userId);
    }

    @Transactional(readOnly = true)
    public ChatRs getByUsers(Long userId, Long correspondenceUser) {
        return ChatRs.from(
                repository.findChatByUsers(userId, correspondenceUser)
                        .orElseThrow(() -> new RuntimeException("Чат между пользователями не найден")), userId
        );
    }

    @Transactional
    public ChatRs create(ChatRq dto, Long userId) {
        final UserEntity firstUser = userService.getEntity(dto.firstUserId());
        final UserEntity secondUser = userService.getEntity(dto.secondUserId());
        checkUsers(dto.firstUserId(), dto.secondUserId());
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setFirstUser(firstUser);
        chatEntity.setSecondUser(secondUser);
        //chatEntity.setCreatedAt(dto.createdAt());

        chatEntity = repository.save(chatEntity);
        return ChatRs.from(chatEntity, userId);
    }

    @Transactional
    public ChatRs delete(Long id, Long userId) {
        final ChatEntity entity = getEntity(id);
        repository.delete(entity);
        return ChatRs.from(entity, userId);
    }
}
