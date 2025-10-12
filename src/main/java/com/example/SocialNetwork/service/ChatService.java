package com.example.SocialNetwork.service;

import com.example.SocialNetwork.api.Chat.ChatRq;
import com.example.SocialNetwork.api.Chat.ChatRs;
import com.example.SocialNetwork.api.NotFoundException;
import com.example.SocialNetwork.api.message.MessageRs;
import com.example.SocialNetwork.entity.ChatEntity;
import com.example.SocialNetwork.entity.PostEntity;
import com.example.SocialNetwork.entity.UserEntity;
import com.example.SocialNetwork.mapper.ChatMapper;
import com.example.SocialNetwork.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    private final ChatRepository repository;
    private final UserService userService;
    private final MessageService messageService;
    private final ChatMapper mapper;


    public ChatService(ChatRepository repository, UserService userService, MessageService messageService, ChatMapper mapper) {
        this.repository = repository;
        this.userService = userService;
        this.messageService = messageService;
        this.mapper = mapper;
    }

    public ChatEntity getEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PostEntity.class, id));
    }

    public List<ChatRs> getAll() {
        return mapper.toRsListDto(repository.findAll());
    }

    public ChatRs get(Long id) {
        final ChatEntity entity = getEntity(id);
        return mapper.toRsDto(entity);
    }

    public ChatRs create(ChatRq dto) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setCreatedAt(dto.getCreatedAt());
        chatEntity.setMessages(new ArrayList<>());

        if (dto.getParticipants() != null) {
            List<UserEntity> participants = dto.getParticipants().stream()
                    .map(userService::getEntity)
                    .collect(Collectors.toList());
            chatEntity.setParticipants(participants);
        }

        chatEntity = repository.save(chatEntity);
        return mapper.toRsDto(chatEntity);
    }

    public ChatRs delete(Long id) {
        final ChatEntity entity = getEntity(id);
        repository.delete(entity);
        return mapper.toRsDto(entity);
    }
}
