package com.example.SocialNetwork.api.Chat;

import com.example.SocialNetwork.api.NotFoundException;
import com.example.SocialNetwork.api.message.MessageController;
import com.example.SocialNetwork.api.user.UserController;
import com.example.SocialNetwork.api.user.UserDTO;
import com.example.SocialNetwork.configuration.Constants;
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
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final AtomicInteger idGenerator = new AtomicInteger();
    private List<ChatDTO> chats = new CopyOnWriteArrayList<>();
    private UserController userController;
    private MessageController messageController;

    public ChatController(UserController userController, MessageController messageController) {
        this.messageController = messageController;

        int userId = 1;

        this.chats = new ArrayList<>(List.of(
                new ChatDTO(idGenerator.incrementAndGet(), new Date(), new ArrayList<>(List.of(1, 2))),
                new ChatDTO(idGenerator.incrementAndGet(), new Date(), new ArrayList<>(List.of(2, 3))),
                new ChatDTO(idGenerator.incrementAndGet(), new Date(), new ArrayList<>(List.of(1, 3)))));

        chats.forEach(chat -> chat.setUser(
                userController.get(chat.getParticipants().stream()
                        .filter(id -> !id.equals(userId)).findFirst().orElse(0))));

        chats.forEach(chat -> chat.setMessages(messageController.getMessagesByChat(chat.getId())));

        this.userController = userController;
    }

    @GetMapping("/userschats/{userId}")
    public List<ChatDTO> getMethodName(@PathVariable int userId) {
        log.debug("Get all user's chats");
        var d = chats.stream().filter(chat -> chat.getParticipants().contains(userId)).toList();
        return d;
    }

    @GetMapping("/{id}")
    public ChatDTO get(@PathVariable int id) {
        log.debug("Get user wtih id {}", id);
        var someChat =  chats.stream()
                .filter(chat -> chat.getId() == id)
                .findAny()
                .orElseThrow(() -> new NotFoundException(UserDTO.class, id));
        someChat.setMessages(messageController.getMessagesByChat(someChat.getId()));
        return someChat;
    }

    @GetMapping("/{userId}/{subscribtedUserId}")
    public ChatDTO get(@PathVariable int userId, @PathVariable int subscribtedUserId) {
        log.debug("Get user wtih id {}", userId);
        var someChat =  chats.stream()
                .filter(chat -> chat.getParticipants().getFirst() == userId || chat.getParticipants().get(1) == userId)
                .findAny()
                .orElseThrow(() -> new NotFoundException(UserDTO.class, userId));
        someChat.setMessages(messageController.getMessagesByChat(someChat.getId()));
        return someChat;
    }

    @PostMapping
    public ChatDTO create(@RequestBody ChatDTO newChat) {
        log.debug("Create chat with data {}", newChat);
        newChat.setId(idGenerator.incrementAndGet());
        chats.add(newChat);
        return newChat;
    }

    @DeleteMapping("/{id}")
    public ChatDTO delete(@PathVariable int id) {
        log.debug("Delete chat with id {}", id);
        final ChatDTO chat = get(id);
        chats.remove(chat);
        return chat;
    }
}
