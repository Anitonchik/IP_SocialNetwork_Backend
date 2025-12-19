package com.example.SocialNetwork.api.post;

import com.example.SocialNetwork.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

import static com.example.SocialNetwork.configuration.Constants.THEME_COOKIE;
import static com.example.SocialNetwork.configuration.Constants.THEME_DEFAULT;

@Profile("!front")
@Controller
public class MvcPageController {
    private final Logger logger = LoggerFactory.getLogger(MvcPageController.class);

    @GetMapping("/test")
    @ResponseBody
    public String getMethodName(
            @CookieValue(name = THEME_COOKIE, defaultValue = THEME_DEFAULT) String theme) {
        return theme;
    }

    @PostMapping("/chat")
    public String postPage3(@RequestParam Map<String, String> allParams) {
        final String params = allParams.entrySet().stream()
                .map(e -> String.format("%s: %s", e.getKey(), e.getValue()))
                .collect(Collectors.joining("\n"));
        logger.info("\n{}", params);
        return "redirect:page3";
    }

    /*@GetMapping("/profile/{id}")
    public String getProfile(@PathVariable Long id, Model model) {
        UserEntity user = profileService.getUserProfile(id);
        List<Post> posts = profileService.getUserPosts(id);

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);

        return "profile/profile"; // имя Thymeleaf-шаблона
    }*/

}
