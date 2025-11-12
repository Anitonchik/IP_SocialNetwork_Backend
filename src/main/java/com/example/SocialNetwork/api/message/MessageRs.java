package com.example.SocialNetwork.api.message;

import com.example.SocialNetwork.api.Chat.ChatRs;
import com.example.SocialNetwork.api.user.UserRs;
import com.example.SocialNetwork.entity.MessageEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

public record MessageRs (Long id, ChatRs chat, UserRs user, String messageText, Date createdAt, Boolean isEdited) {
    public static MessageRs from(MessageEntity message) {
        return new MessageRs(message.getId(), ChatRs.from(message.getChat(), message.getUser().getId()), UserRs.from(message.getUser()), message.getMessageText(),
                message.getCreatedAt(), message.getIsEdited());
    }

    public static List<MessageRs> fromList(Iterable<MessageEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(MessageRs::from)
                .toList();
    }

}
