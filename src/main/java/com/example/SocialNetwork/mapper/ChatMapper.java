package com.example.SocialNetwork.mapper;

import com.example.SocialNetwork.api.Chat.ChatRq;
import com.example.SocialNetwork.api.Chat.ChatRs;
import com.example.SocialNetwork.api.message.MessageRq;
import com.example.SocialNetwork.api.message.MessageRs;
import com.example.SocialNetwork.api.user.UserRq;
import com.example.SocialNetwork.api.user.UserRs;
import com.example.SocialNetwork.entity.ChatEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class ChatMapper {
    private UserMapper userMapper;
    private MessageMapper messageMapper;

    public ChatMapper(UserMapper userMapper, MessageMapper messageMapper){
        this.userMapper = userMapper;
        this.messageMapper = messageMapper;
    }

    public ChatRq toRgDto(Date createdAt, List<UserRq> participants, List<MessageRq> messages) {
        final ChatRq dto = new ChatRq();
        dto.setCreatedAt(createdAt);
        dto.setParticipants(participants);
        dto.setMessages(messages);
        return dto;
    }

    public ChatRs toRsDto(ChatEntity chatEntity){
        List<UserRs> users = new ArrayList<>();
        for (int i = 0; i < chatEntity.getParticipants().size(); i++) {
            var user = chatEntity.getParticipants().get(i);
            users.add(userMapper.toRsDto(user));
        }

        List<MessageRs> messages = new ArrayList<>();
        for (int i = 0; i < chatEntity.getMessages().size(); i++) {
            var message = chatEntity.getMessages().get(i);
            messages.add(messageMapper.toRsDto(message));
        }

        final ChatRs dto = new ChatRs();
        dto.setCreatedAt(chatEntity.getCreatedAt());
        dto.setParticipants(users);
        dto.setMessages(messages);

        return dto;
    }

    public List<ChatRs> toRsListDto(Iterable<ChatEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::toRsDto)
                .toList();
    }
}
