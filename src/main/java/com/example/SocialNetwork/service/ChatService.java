package com.example.SocialNetwork.service;

import com.example.SocialNetwork.api.Chat.ChatRq;
import com.example.SocialNetwork.api.Chat.ChatRs;
import com.example.SocialNetwork.api.NotFoundException;
import com.example.SocialNetwork.api.Post.PostRs;
import com.example.SocialNetwork.entity.ChatEntity;
import com.example.SocialNetwork.entity.PostEntity;
import com.example.SocialNetwork.entity.UserEntity;
import com.example.SocialNetwork.mapper.ChatMapper;
import com.example.SocialNetwork.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    private final ChatRepository repository;
    private final UserService userService;
    private final ChatMapper mapper;


    public ChatService(ChatRepository repository, UserService userService, ChatMapper mapper) {
        this.repository = repository;
        this.userService = userService;
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

        if (dto.getParticipants() != null) {
            List<UserEntity> participants = dto.getParticipants().stream()
                    .map(userService::getEntity)
                    .collect(Collectors.toList());
            chatEntity.setParticipants(participants);
        }
        chatEntity = repository.save(chatEntity);
        return mapper.toRsDto(chatEntity);
    }

    public ChatRs update(Long id, ChatRq dto) {
        ChatEntity entity = getEntity(id);
        entity.setCreatedAt(dto.getCreatedAt());

        if (dto.getParticipants() != null && !dto.getParticipants().isEmpty()) {
            List<UserEntity> participants = dto.getParticipants().stream()
                    .map(userService::getEntity)
                    .collect(Collectors.toList());
            entity.setParticipants(participants);
        }

        entity = repository.save(entity);
        return mapper.toRsDto(entity);
    }

    public ChatRs delete(Long id) {
        final ChatEntity entity = getEntity(id);
        repository.delete(entity);
        return mapper.toRsDto(entity);
    }
}
