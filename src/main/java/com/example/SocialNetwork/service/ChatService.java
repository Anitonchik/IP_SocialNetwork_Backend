package com.example.SocialNetwork.service;

import com.example.SocialNetwork.api.Chat.ChatRq;
import com.example.SocialNetwork.api.Chat.ChatRs;
import com.example.SocialNetwork.api.NotFoundException;
import com.example.SocialNetwork.api.user.UserRs;
import com.example.SocialNetwork.entity.ChatEntity;
import com.example.SocialNetwork.entity.PostEntity;
import com.example.SocialNetwork.entity.UserEntity;
import com.example.SocialNetwork.error.AlreadyExistsException;
import com.example.SocialNetwork.repository.ChatRepository;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional(propagation = Propagation.MANDATORY)
    public ChatEntity getEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PostEntity.class, id));
    }

    @Transactional(readOnly = true)
    public List<ChatRs> getAll() {
        return ChatRs.fromList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public ChatRs get(Long id) {
        final ChatEntity entity = getEntity(id);
        return ChatRs.from(entity);
    }

    @Transactional(readOnly = true)
    public List<ChatRs> getByUser(Long userId) {
        return ChatRs.fromList(repository.findUsersChats(userId));
    }

    @Transactional(readOnly = true)
    public ChatRs getByUsers(Long firstUserId, Long secondUserId) {
        return ChatRs.from(
                repository.findChatByUsers(firstUserId, secondUserId)
                        .orElseThrow(() -> new RuntimeException("Чат между пользователями не найден"))
        );
    }

    @Transactional
    public ChatRs create(ChatRq dto) {
        final UserEntity firstUser = userService.getEntity(dto.firstUserId());
        final UserEntity secondUser = userService.getEntity(dto.secondUserId());
        checkUsers(dto.firstUserId(), dto.secondUserId());
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setFirstUser(firstUser);
        chatEntity.setFirstUser(secondUser);
        chatEntity.setCreatedAt(dto.createdAt());
        //chatEntity.setMessages(new ArrayList<>());

        chatEntity = repository.save(chatEntity);
        return ChatRs.from(chatEntity);
    }

    @Transactional
    public ChatRs delete(Long id) {
        final ChatEntity entity = getEntity(id);
        repository.delete(entity);
        return ChatRs.from(entity);
    }
}
