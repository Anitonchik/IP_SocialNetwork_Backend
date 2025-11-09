package com.example.SocialNetwork.api.message;

import com.example.SocialNetwork.entity.MessageEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

public record MessageRs (Long id, Long chatId, Long userId, String messageText, Date createdAt, Boolean isEdited) {
    public static MessageRs from(MessageEntity message) {
        return new MessageRs(message.getId(), message.getChatId(), message.getUserId(), message.getMessageText(),
                message.getCreatedAt(), message.getIsEdited());
    }

    public static List<MessageRs> fromList(Iterable<MessageEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(MessageRs::from)
                .toList();
    }

}
