package com.example.SocialNetwork.service;

import com.example.SocialNetwork.api.chat.ChatRq;
import com.example.SocialNetwork.api.chat.ChatRs;
import com.example.SocialNetwork.api.PageRs;
import com.example.SocialNetwork.error.NotFoundException;
import com.example.SocialNetwork.entity.ChatEntity;
import com.example.SocialNetwork.entity.UserEntity;
import com.example.SocialNetwork.error.AlreadyExistsException;
import com.example.SocialNetwork.repository.ChatRepository;
import com.example.SocialNetwork.repository.MessageRepository;
import com.example.SocialNetwork.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatService {
    private final ChatRepository repository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;


    public ChatService(ChatRepository repository, UserRepository userRepository, MessageRepository messageRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public Boolean checkUsers(Long firstUserId, Long secondUserId) {
        var chat = repository.findChatByUsers(firstUserId, secondUserId);
        return chat.isPresent();
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
        final UserEntity firstUser = userRepository.findById(dto.firstUserId()).get();
        final UserEntity secondUser = userRepository.findById(dto.secondUserId()).get();
        if (!checkUsers(dto.firstUserId(), dto.secondUserId())) {
            ChatEntity chatEntity = new ChatEntity();
            chatEntity.setFirstUser(firstUser);
            chatEntity.setSecondUser(secondUser);

            chatEntity = repository.save(chatEntity);
            return ChatRs.from(chatEntity, userId);
        }
        else {
            return getByUsers(firstUser.getId(), secondUser.getId());
        }
    }

    @Transactional
    public ChatRs delete(Long id, Long userId) {
        final ChatEntity entity = getEntity(id);
        messageRepository.deleteAllByChat_Id(id);
        repository.delete(entity);
        return ChatRs.from(entity, userId);
    }

    public void deleteChatsByUserId(Long userId) {
        var chats = getByUser(userId);
        for (var chat : chats) {
            messageRepository.deleteAllByChat_Id(chat.id());
        }
        repository.deleteAllByFirstUserId(userId);
        repository.deleteAllBySecondUserId(userId);
    }
}
