package com.example.SocialNetwork.service;

import com.example.SocialNetwork.api.NotFoundException;
import com.example.SocialNetwork.api.message.MessageRq;
import com.example.SocialNetwork.api.message.MessageRs;
import com.example.SocialNetwork.entity.MessageEntity;
import com.example.SocialNetwork.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public MessageEntity getEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageEntity.class, id));
    }

    @Transactional(readOnly = true)
    public List<MessageRs> getAll() {
        return MessageRs.fromList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public MessageRs get(Long id) {
        final MessageEntity entity = getEntity(id);
        return MessageRs.from(entity);
    }

    @Transactional(readOnly = true)
    public List<MessageRs> getByChat(Long chatId) {
        return MessageRs.fromList(repository.findByChatId(chatId));
    }

    @Transactional
    public MessageRs create(MessageRq dto) {
        MessageEntity entity = new MessageEntity(dto.chatId(), dto.userId(), dto.messageText(), dto.createdAt());
        entity = repository.save(entity);
        return MessageRs.from(entity);
    }

    @Transactional
    public MessageRs update(Long id, MessageRq dto) {
        MessageEntity entity = getEntity(id);
        entity.setChatId(dto.chatId());
        entity.setUserId(dto.userId());
        entity.setMessageText(dto.messageText());
        entity.setCreatedAt(dto.createdAt());
        entity = repository.save(entity);
        return MessageRs.from(entity);
    }

    public MessageRs delete(Long id) {
        final MessageEntity entity = getEntity(id);
        repository.delete(entity);
        return MessageRs.from(entity);
    }
}
