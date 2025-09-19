package com.example.SocialNetwork.api.Chat;

import com.example.SocialNetwork.api.NotFoundException;
import com.example.SocialNetwork.api.User.UserController;
import com.example.SocialNetwork.api.User.UserDTO;
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
@RequestMapping(Constants.API_URL + MessageController.URL)
public class MessageController {
    public static final String URL = "/messages";
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final AtomicInteger idGenerator = new AtomicInteger();
    private List<MessageDTO> messages = new CopyOnWriteArrayList<>();

    public MessageController() {
        this.messages = new ArrayList<>(List.of(
                new MessageDTO(idGenerator.incrementAndGet(), 1, 1, "blablabla",
                        new Date(), new ArrayList<>(), new ArrayList<>(List.of(new ReadStatusDTO(1, new Date())))),
                new MessageDTO(idGenerator.incrementAndGet(), 1, 1, "blablabla",
                        new Date(), new ArrayList<>(), new ArrayList<>(List.of(new ReadStatusDTO(1, new Date())))),
                new MessageDTO(idGenerator.incrementAndGet(), 1, 1, "blablabla",
                        new Date(), new ArrayList<>(), new ArrayList<>(List.of(new ReadStatusDTO(1, new Date()))))));
    }

    @GetMapping
    public List<MessageDTO> getMethodName() {
        log.debug("Get all messages");
        return messages.stream().toList();
    }

    @GetMapping("/{id}")
    public MessageDTO get(@PathVariable int id) {
        log.debug("Get user wtih id {}", id);
        return messages.stream()
                .filter(chat -> chat.getId() == id)
                .findAny()
                .orElseThrow(() -> new NotFoundException(UserDTO.class, id));
    }

    @GetMapping("/fromChat/{chatId}")
    public List<MessageDTO> getMessagesByChat(@PathVariable int chatId) {
        log.debug("Get messages id {}", chatId);
        return messages.stream()
                .filter(chat -> chat.getId() == chatId).toList();
    }

    @PostMapping
    public MessageDTO create(@RequestBody MessageDTO newMessage) {
        log.debug("Create message with data {}", newMessage);
        newMessage.setId(idGenerator.incrementAndGet());
        messages.add(newMessage);
        return newMessage;
    }

    @PutMapping("/{id}")
    public MessageDTO edit(@PathVariable int id, @RequestBody MessageDTO newMessage) {
        log.debug("Edit message with id {} and data {}", id, newMessage);
        final MessageDTO existsMessage = get(id);
        existsMessage.setMessageText(newMessage.getMessageText());
        existsMessage.setAttachments(newMessage.getAttachments());
        existsMessage.setCreatedAt(newMessage.getCreatedAt());
        return existsMessage;
    }

    @DeleteMapping("/{id}")
    public MessageDTO delete(@PathVariable int id) {
        log.debug("Delete chat with id {}", id);
        final MessageDTO chat = get(id);
        messages.remove(chat);
        return chat;
    }

}
