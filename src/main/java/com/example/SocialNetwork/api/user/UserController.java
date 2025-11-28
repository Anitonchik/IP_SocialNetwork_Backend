package com.example.SocialNetwork.api.user;

import com.example.SocialNetwork.configuration.Constants;
import com.example.SocialNetwork.entity.Report;
import com.example.SocialNetwork.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/followers/{id}")
    public List<UserRs> getFollowers(@PathVariable Long id) {
        return userService.getFollowers(id);
    }

    @GetMapping("/subscriptions/{id}")
    public List<UserRs> getSubscriptions(@PathVariable Long id) {
        return userService.getSubscriptions(id);
    }

    @GetMapping("/subscription/{id}/{userFollowingId}")
    public Boolean getSubscription(@PathVariable Long id, @PathVariable Long userFollowingId) {
        return userService.getSubscription(id, userFollowingId);
    }

    @GetMapping("statistic/{id}")
    public List<Report> getStatistic(@PathVariable Long id) {
        return userService.getStatistic(id);
    }

    @DeleteMapping("/subscription/{id}/{userFollowingId}")
    public List<UserRs> deleteSubscription(@PathVariable Long id, @PathVariable Long userFollowingId) {
        return userService.deleteSubscription(id, userFollowingId);
    }

    @PutMapping("/{id}")
    public UserRs update(@PathVariable Long id, @RequestBody @Valid UserRq dto) {
        return userService.update(id, dto);
    }

    //subscribedUserId - на кого подписываюсь
    @PutMapping("/{id}/{userFollowingId}")
    public UserRs createSubscriptions(@PathVariable Long id, @PathVariable Long userFollowingId) {
        return userService.createSubscription(id, userFollowingId);
    }

    @DeleteMapping("/{id}")
    public UserRs delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
