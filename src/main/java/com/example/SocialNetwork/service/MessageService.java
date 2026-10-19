package com.example.SocialNetwork.service;

import com.example.SocialNetwork.api.PageRs;
import com.example.SocialNetwork.api.user.UserRs;
import com.example.SocialNetwork.error.NotFoundException;
import com.example.SocialNetwork.api.message.MessageRq;
import com.example.SocialNetwork.api.message.MessageRs;
import com.example.SocialNetwork.entity.MessageEntity;
import com.example.SocialNetwork.error.NotEqualsIdException;
import com.example.SocialNetwork.repository.MessageRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class MessageService {
    private final MessageRepository repository;
    private final ChatService chatService;
    private final UserService userService;

    public MessageService(MessageRepository repository, ChatService chatService, UserService userService) {
        this.repository = repository;
        this.chatService = chatService;
        this.userService = userService;
    }

    //@Transactional(propagation = Propagation.MANDATORY)
    @Transactional(readOnly = true)
    public MessageEntity getEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageEntity.class, id));
    }

    @Transactional(readOnly = true)
    public PageRs<MessageRs> getAll(Pageable pageable) {
        return PageRs.from(repository.findAll(pageable), MessageRs::from);
    }


    @Transactional(readOnly = true)
    public MessageRs get(Long id) {
        final MessageEntity entity = getEntity(id);
        return MessageRs.from(entity);
    }

    @Transactional(readOnly = true)
    public List<MessageRs> getByChat(Long chatId) {
        return MessageRs.fromList(repository.findByChat_IdOrderByCreatedAtAsc(chatId));
    }

    @Transactional
    public MessageRs create(MessageRq dto) {
        final var chat = chatService.getEntity(dto.chatId());

        if (!Objects.equals(chat.getFirstUser().getId(), dto.userId())
                && !Objects.equals(chat.getSecondUser().getId(), dto.userId())) {
            throw new NotEqualsIdException(chat.getFirstUser().getId(), chat.getSecondUser().getId(), dto.userId());
        }

        final var user = userService.getEntity(dto.userId());
        MessageEntity entity = new MessageEntity(chat, user, dto.messageText(), dto.createdAt());
        entity = repository.save(entity);
        return MessageRs.from(entity);
    }

    @Transactional
    public MessageRs update(Long id, MessageRq dto) {
        MessageEntity entity = getEntity(id);
        final var chat = chatService.getEntity(dto.chatId());
        final var user = userService.getEntity(dto.userId());
        entity.setChat(chat);
        entity.setUser(user);
        entity.setMessageText(dto.messageText());
        entity.setCreatedAt(dto.createdAt());
        entity.setIsEdited(true);
        entity = repository.save(entity);
        return MessageRs.from(entity);
    }

    @Transactional
    public MessageRs delete(Long id) {
        final MessageEntity entity = getEntity(id);
        repository.delete(entity);
        return MessageRs.from(entity);
    }
}
