package com.example.SocialNetwork.api.Post;

import com.example.SocialNetwork.api.Chat.ChatRq;
import com.example.SocialNetwork.api.Chat.ChatRs;
import com.example.SocialNetwork.api.NotFoundException;
import com.example.SocialNetwork.api.user.UserController;
import com.example.SocialNetwork.configuration.Constants;
import com.example.SocialNetwork.service.ChatService;
import com.example.SocialNetwork.service.PostService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(Constants.API_URL + PostController.URL)
public class PostController {
    public static final String URL = "/posts";
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostRs> getAll() {
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public PostRs get(@PathVariable Long id) {
        return postService.get(id);
    }

    @PostMapping
    public PostRs create(@RequestBody @Valid PostRq dto) {
        return postService.create(dto);
    }

    @PutMapping("/{id}")
    public PostRs update(@PathVariable Long id, @RequestBody @Valid PostRq dto) {
        return postService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public PostRs delete(@PathVariable Long id) {
        return postService.delete(id);
    }
}
