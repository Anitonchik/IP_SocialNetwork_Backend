package com.example.SocialNetwork.mapper;

import com.example.SocialNetwork.api.message.MessageRq;
import com.example.SocialNetwork.api.message.MessageRs;
import com.example.SocialNetwork.entity.MessageEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class MessageMapper {
    public MessageRq toRgDto(Long chatId, Long userId, String messageText, Boolean isEdited, Date createdAt) {
        final MessageRq dto = new MessageRq();
        dto.setChatId(chatId);
        dto.setUserId(userId);
        dto.setMessageText(messageText);
        dto.setIsEdited(isEdited);
        dto.setCreatedAt(createdAt);
        return dto;
    }

    public MessageRs toRsDto(MessageEntity messageEntity){
        final MessageRs dto = new MessageRs();
        dto.setId(messageEntity.getId());
        dto.setChatId(messageEntity.getChatId());
        dto.setUserId(messageEntity.getUserId());
        dto.setMessageText(messageEntity.getMessageText());
        dto.setIsEdited(messageEntity.getIsEdited());
        dto.setCreatedAt(messageEntity.getCreatedAt());
        return dto;
    }

    public List<MessageRs> toRsListDto(Iterable<MessageEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::toRsDto)
                .toList();
    }
}
