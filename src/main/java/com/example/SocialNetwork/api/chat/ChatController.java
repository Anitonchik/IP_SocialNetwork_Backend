package com.example.SocialNetwork.api.chat;

import com.example.SocialNetwork.api.PageHelper;
import com.example.SocialNetwork.api.PageRs;
import com.example.SocialNetwork.configuration.Constants;
import com.example.SocialNetwork.service.ChatService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.API_URL + ChatController.URL)
public class ChatController {
    public static final String URL = "/chats";
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("{userId}")
    public PageRs<ChatRs> getAll(@RequestParam(defaultValue = "1") @Min(1) int page,
                                 @RequestParam(defaultValue = "10") @Min(1) int size,
                                 @PathVariable Long userId) {
        return chatService.getAll(PageHelper.toPageable(page, size), userId);
    }

    @GetMapping("/{chatId}/{userId}")
    public ChatRs getChatById(@PathVariable Long chatId, @PathVariable Long userId) {
        return chatService.get(chatId, userId);
    }

    @GetMapping("/userschats/{userId}")
    public List<ChatRs> getChatsByUser(@PathVariable Long userId) {return chatService.getByUser(userId);}

    @GetMapping("/userschats/checkavailability/{userId}/{subscribedUserId}")
    public Boolean getChatsByUser(@PathVariable Long userId, @PathVariable Long subscribedUserId) {return chatService.checkUsers(userId, subscribedUserId);}

    @GetMapping("/userschat/{userId}/{subscribedUserId}")
    public ChatRs getChatByUsers(@PathVariable Long userId, @PathVariable Long subscribedUserId) {return chatService.getByUsers(userId, subscribedUserId);}

    @PostMapping("{userId}")
    public ChatRs create(@PathVariable Long userId, @RequestBody @Valid ChatRq dto) {
        return chatService.create(dto, userId);
    }


    @DeleteMapping("/{chatId}/{userId}")
    public ChatRs delete(@PathVariable Long chatId, @PathVariable Long userId) {
        return chatService.delete(chatId, userId);
    }
}
