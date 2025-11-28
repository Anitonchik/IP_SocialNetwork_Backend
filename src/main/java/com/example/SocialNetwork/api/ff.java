package com.example.SocialNetwork.api;

import com.example.SocialNetwork.api.Chat.ChatRs;
import com.example.SocialNetwork.entity.ChatEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public record ff (
        List<ChatRs> items,
        int itemsCount,
        int currentPage,
        int currentSize,
        int totalPages,
        long totalItems,
        boolean isFirst,
        boolean isLast,
        boolean hasNext,
        boolean hasPrevious) {

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
}
