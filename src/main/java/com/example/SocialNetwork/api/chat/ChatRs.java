package com.example.SocialNetwork.api.chat;

import com.example.SocialNetwork.api.message.MessageRs;
import com.example.SocialNetwork.api.user.UserRs;
import com.example.SocialNetwork.entity.ChatEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

public record ChatRs (Long id, UserRs correspondenceUser, List<MessageRs> messages){


    public static ChatRs from(ChatEntity chat, Long userId) {
        return new ChatRs(chat.getId(), UserRs.from(Objects.equals(chat.getFirstUser().getId(), userId)
                        ? chat.getSecondUser() : chat.getFirstUser(), false),
                chat.getMessages() != null ? MessageRs.fromList(chat.getMessages()) : new ArrayList<>());
    }

    public static List<ChatRs> fromList(Iterable<ChatEntity> entities, Long userId) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(chat -> ChatRs.from(chat, userId))
                .toList();
    }
}

