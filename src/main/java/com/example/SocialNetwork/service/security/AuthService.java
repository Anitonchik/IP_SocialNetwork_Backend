package com.example.SocialNetwork.service.security;

import com.example.SocialNetwork.api.security.UserJWTRs;
import com.example.SocialNetwork.api.user.UserLoginRq;
import com.example.SocialNetwork.api.user.UserRq;
import com.example.SocialNetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    public UserJWTRs login (UserLoginRq user) {
        String username = user.username();
        String password = user.password();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }

        var userEntity = userService.getBuUserName(username);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String jwt = jwtService.generateToken(userDetails);
        return new UserJWTRs(jwt, userEntity.getId(), userEntity.getUserRole().toString());
    }

    public UserJWTRs register(UserRq user) {
        userService.register(user);
        return login(new UserLoginRq(user.userName(), user.password()));
    }
}
