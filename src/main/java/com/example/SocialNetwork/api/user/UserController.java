package com.example.SocialNetwork.api.user;

import com.example.SocialNetwork.api.NotFoundException;
import com.example.SocialNetwork.configuration.Constants;
import com.example.SocialNetwork.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(Constants.API_URL + UserController.URL)
public class UserController {
    public static final String URL = "/users";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<UserRs> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserRs get(@PathVariable Long id) {
        return userService.get(id);
    }

    @PostMapping
    public UserRs create(@RequestBody @Valid UserRq dto) {
        return userService.create(dto);
    }

    @PutMapping("/{id}")
    public UserRs update(@PathVariable Long id, @RequestBody @Valid UserRq dto) {
        return userService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public UserRs delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
