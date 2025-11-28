package com.example.SocialNetwork.api.Post;

import com.example.SocialNetwork.configuration.Constants;
import com.example.SocialNetwork.service.PostService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/usersPosts/{userId}")
    public List<PostRs> getPostsByUser(@PathVariable Long userId) {return postService.getByUser(userId);}

    @GetMapping("/notUsersPosts/{userId}")
    public List<PostRs> getPostsByNotUser(@PathVariable Long userId) {return postService.getNotByUser(userId);}

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
