package com.example.SocialNetwork.api.Chat;

import com.example.SocialNetwork.api.message.MessageRs;
import com.example.SocialNetwork.api.user.UserRq;
import com.example.SocialNetwork.api.user.UserRs;
import com.example.SocialNetwork.entity.ChatEntity;
import com.example.SocialNetwork.entity.MessageEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

public record ChatRs (Long id, UserRs firstUser, UserRs secondUser, Date createdAt, List<MessageRs> messages){

    public static ChatRs from(ChatEntity chat) {
        return new ChatRs(chat.getId(), UserRs.from(chat.getFirstUser()), UserRs.from(chat.getSecondUser()),
                chat.getCreatedAt(), MessageRs.fromList(chat.getMessages()));
    }

    public static List<ChatRs> fromList(Iterable<ChatEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(ChatRs::from)
                .toList();
    }
}

