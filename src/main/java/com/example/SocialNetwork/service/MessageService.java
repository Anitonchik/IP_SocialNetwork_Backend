package com.example.SocialNetwork.service;

import com.example.SocialNetwork.api.NotFoundException;
import com.example.SocialNetwork.api.message.MessageRq;
import com.example.SocialNetwork.api.message.MessageRs;
import com.example.SocialNetwork.entity.MessageEntity;
import com.example.SocialNetwork.mapper.MessageMapper;
import com.example.SocialNetwork.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository repository;
    private final MessageMapper mapper;

    public MessageService(MessageRepository repository, MessageMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public MessageEntity getEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageEntity.class, id));
    }

    public List<MessageRs> getAll() {
        return mapper.toRsListDto(repository.findAll());
    }

    public MessageRs get(Long id) {
        final MessageEntity entity = getEntity(id);
        return mapper.toRsDto(entity);
    }

    public MessageRs create(MessageRq dto) {
        MessageEntity entity = new MessageEntity(
                dto.getChatId(),
                dto.getUserId(),
                dto.getMessageText(),
                dto.getCreatedAt());
        entity = repository.save(entity);
        return mapper.toRsDto(entity);
    }

    public MessageRs update(Long id, MessageRq dto) {
        MessageEntity entity = getEntity(id);
        entity.setChatId(dto.getChatId());
        entity.setUserId(dto.getUserId());
        entity.setMessageText(dto.getMessageText());
        entity.setCreatedAt(dto.getCreatedAt());
        entity = repository.save(entity);
        return mapper.toRsDto(entity);
    }

    public MessageRs delete(Long id) {
        final MessageEntity entity = getEntity(id);
        repository.delete(entity);
        return mapper.toRsDto(entity);
    }
}
