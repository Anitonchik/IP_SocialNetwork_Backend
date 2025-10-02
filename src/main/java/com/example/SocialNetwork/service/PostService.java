package com.example.SocialNetwork.service;

import com.example.SocialNetwork.api.NotFoundException;
import com.example.SocialNetwork.api.Post.PostDTO;
import com.example.SocialNetwork.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    final private PostRepository repository;


    public PostService(PostRepository repository) {
        this.repository = repository;
    }


    @GetMapping
    public List<PostDTO> getPosts() {
        return posts;
    }

    @GetMapping("/usersPosts/{userId}")
    public List<PostDTO> getPostsByUser(@PathVariable int userId) {
        List<PostDTO> userPosts = new ArrayList<>();
        posts.stream().filter(postDTO -> postDTO.getUser().getId() == userId).forEach(userPosts::add);
        return userPosts;
    }

    @GetMapping("/notUsersPosts/{userId}")
    public List<PostDTO> getPostsNotByUser(@PathVariable int userId) {
        List<PostDTO> notUsersPosts = new ArrayList<>();
        posts.stream().filter(postDTO -> postDTO.getUser().getId() != userId).forEach(notUsersPosts::add);
        return notUsersPosts;
    }

    @GetMapping("/{id}")
    public PostDTO get(@PathVariable int id) {
        return posts.stream()
                .filter(group -> group.getId() == id)
                .findAny()
                .orElseThrow(() -> new NotFoundException(PostDTO.class, id));
    }

    @PostMapping
    public PostDTO create(@RequestBody PostDTO newPost){
        newPost.setId(idGenerator.incrementAndGet());
        posts.add(newPost);

        return posts.getLast();
    }

    @PutMapping("/{id}")
    public PostDTO update(@PathVariable int id, @RequestBody PostDTO newPost) {
        log.debug("Edit student with id {} and data {}", id, newPost);
        final PostDTO existsPost = get(id);
        existsPost.setPostImageURL(newPost.getPostImageURL());
        existsPost.setPostTextContent(newPost.getPostTextContent());
        return existsPost;
    }

    @DeleteMapping("/{id}")
    public PostDTO delete(@PathVariable int id) {
        log.debug("Delete student with id {}", id);
        final PostDTO post = get(id);
        posts.remove(post);
        return post;
    }
}
