package com.example.SocialNetwork.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Profile("!front")
@Configuration
public class MvcWebConfiguration implements WebMvcConfigurer {
    @Override
    public void addViewControllers(@NonNull ViewControllerRegistry registry) {
        // Если не нужны данные из глобального контроллера
        registry.addViewController("/posts").setViewName("posts");
        registry.addViewController("/chats").setViewName("chats");

        registry.addRedirectViewController("/", "/posts");
    }
}
