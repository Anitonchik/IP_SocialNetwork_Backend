package com.example.SocialNetwork.api.security;

import com.example.SocialNetwork.api.user.UserLoginRq;
import com.example.SocialNetwork.api.user.UserRq;
import com.example.SocialNetwork.configuration.Constants;
import com.example.SocialNetwork.service.JwtService;
import com.example.SocialNetwork.service.UserDetailsServiceImpl;
import com.example.SocialNetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping(Constants.LOGIN_URL)
    public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginRq user) {
        String username = user.username();
        String password = user.password();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid username or password"));
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String jwt = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(Map.of("token", jwt));
    }

    @PostMapping()
    public ResponseEntity<String> register(@RequestBody UserRq user) {
        userService.register(user);
        return ResponseEntity.ok("User registered successfully");
    }
}