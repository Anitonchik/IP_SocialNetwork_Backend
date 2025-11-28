package com.example.SocialNetwork.api.message;

import com.example.SocialNetwork.configuration.Constants;
import com.example.SocialNetwork.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.API_URL + MessageController.URL)
public class MessageController {
    public static final String URL = "/messages";

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<MessageRs> getAll() {
        return messageService.getAll();
    }

    @GetMapping("/{id}")
    public MessageRs get(@PathVariable Long id) {
        return messageService.get(id);
    }

    @GetMapping("/fromChat/{chatId}")
    public List<MessageRs> getMessagesByChat(@PathVariable Long chatId) {return messageService.getByChat(chatId);}

    @PostMapping
    public MessageRs create(@RequestBody @Valid MessageRq dto) {
        return messageService.create(dto);
    }

    @PutMapping("/{id}")
    public MessageRs update(@PathVariable Long id, @RequestBody @Valid MessageRq dto) {
        return messageService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public MessageRs delete(@PathVariable Long id) {
        return messageService.delete(id);
    }

}
