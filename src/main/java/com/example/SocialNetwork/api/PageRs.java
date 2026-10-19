package com.example.SocialNetwork.api;

import com.example.SocialNetwork.api.chat.ChatRs;
import com.example.SocialNetwork.api.user.UserRs;
import com.example.SocialNetwork.entity.ChatEntity;
import com.example.SocialNetwork.entity.UserEntity;
import com.example.SocialNetwork.service.UserService;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public record PageRs<D>(
        List<D> items,
        int itemsCount,
        int currentPage,
        int currentSize,
        int totalPages,
        long totalItems,
        boolean isFirst,
        boolean isLast,
        boolean hasNext,
        boolean hasPrevious) {

    public List<D> items() {
        return Optional.ofNullable(items).orElse(Collections.emptyList());
    }

    public static <D, E> PageRs<D> from(Page<E> page, Function<E, D> mapper) {
        return new PageRs<>(
                page.getContent().stream().map(mapper).toList(),
                page.getNumberOfElements(),
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.isLast(),
                page.hasNext(),
                page.hasPrevious());
    }

    public static PageRs<ChatRs> from(Page<ChatEntity> page, long userId) {
        return new PageRs<>(
                page.getContent().stream().map(chat -> ChatRs.from(chat, userId)).toList(),
                page.getNumberOfElements(),
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.isLast(),
                page.hasNext(),
                page.hasPrevious());
    }

    public static PageRs<UserRs> from(Page<UserEntity> page, Long userAuthId, UserService userService) {
        var usersRs = UserRs.fromList(page.getContent(), userAuthId, userService);
        return new PageRs<>(
                usersRs,
                page.getNumberOfElements(),
                page. getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.isLast(),
                page.hasNext(),
                page. hasPrevious());
    }


}
