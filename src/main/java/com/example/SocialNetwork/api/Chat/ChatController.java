package com.example.SocialNetwork.api.Chat;

import com.example.SocialNetwork.api.NotFoundException;
import com.example.SocialNetwork.api.message.MessageController;
import com.example.SocialNetwork.api.user.UserController;
import com.example.SocialNetwork.api.user.UserDTO;
import com.example.SocialNetwork.api.user.UserRs;
import com.example.SocialNetwork.configuration.Constants;
import com.example.SocialNetwork.service.ChatService;
import com.example.SocialNetwork.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(Constants.API_URL + ChatController.URL)
public class ChatController {
    public static final String URL = "/chats";
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public List<ChatRs> getAll() {
        return chatService.getAll();
    }

    @GetMapping("/{id}")
    public ChatRs get(@PathVariable Long id) {
        return chatService.get(id);
    }

    @GetMapping("/userschats/{userId}")
    public List<ChatRs> getChatsByUser(@PathVariable Long userId) {return chatService.getByUser(userId);}

    @GetMapping("/{userId}/{subscribedUserId}")
    public ChatRs get(@PathVariable Long userId, @PathVariable Long subscribedUserId) {return chatService.getByUsers(userId, subscribedUserId);}

    @PostMapping
    public ChatRs create(@RequestBody @Valid ChatRq dto) {
        return chatService.create(dto);
    }


    @DeleteMapping("/{id}")
    public ChatRs delete(@PathVariable Long id) {
        return chatService.delete(id);
    }
}
