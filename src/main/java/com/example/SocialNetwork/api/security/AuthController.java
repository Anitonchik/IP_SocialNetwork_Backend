package com.example.SocialNetwork.api.security;

import com.example.SocialNetwork.api.user.UserLoginRq;
import com.example.SocialNetwork.api.user.UserRq;
import com.example.SocialNetwork.configuration.Constants;
import com.example.SocialNetwork.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(Constants.LOGIN_URL)
    public UserJWTRs login(@RequestBody UserLoginRq user) {
        return authService.login(user);
    }

    @PostMapping(Constants.SIGNUP_URL)
    public UserJWTRs register(@RequestBody UserRq user) {
        return authService.register(user);
    }
}