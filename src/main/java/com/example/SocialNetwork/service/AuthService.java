package com.example.SocialNetwork.service;

import com.example.SocialNetwork.api.user.userSecurity.UserLoginRq;
import com.example.SocialNetwork.error.InvalidTokenException;
import com.example.SocialNetwork.util.JwtTokenUtil;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Profile("front")
@Service
public class AuthService {
    private final Set<String> blacklistedTokens = ConcurrentHashMap.newKeySet();

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthService(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    private String extractTokenId(String token) {
        if (!StringUtils.hasText(token)) {
            throw new InvalidTokenException();
        }
        return DigestUtils.md5DigestAsHex(token.getBytes());
    }

    public String authenticate(UserLoginRq dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.userName(), dto.password()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(dto.userName());
        return jwtTokenUtil.generateToken(userDetails);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(extractTokenId(token));
    }

    public void logout(String token) {
        blacklistedTokens.add(extractTokenId(token));
    }
}
