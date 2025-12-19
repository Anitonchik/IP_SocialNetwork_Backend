package com.example.SocialNetwork.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Profile("!front")
@Configuration
public class CacheConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/webjars/**", "/images/*")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/public/images/")
                .setCachePeriod(3600 * 24);
    }
}
