package com.example.SocialNetwork.api.user.userSecurity;

import com.example.SocialNetwork.api.user.UserRq;
import com.example.SocialNetwork.configuration.Constants;
import com.example.SocialNetwork.entity.UserRole;
import com.example.SocialNetwork.security.UserPrincipal;
import com.example.SocialNetwork.service.AuthService;
import com.example.SocialNetwork.service.UserService;
import com.example.SocialNetwork.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Profile("front")
@RestController
@RequestMapping(AuthController.URL)
public class AuthController {
    public static final String URL = Constants.API_URL + "/auth";

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping(Constants.LOGIN_URL)
    public String login(@RequestBody @Valid UserLoginRq dto) {
        return authService.authenticate(dto);
    }

    @PostMapping(Constants.LOGOUT_URL)
    public boolean logout(HttpServletRequest request) {
        authService.logout(JwtTokenUtil.getTokenFromRequest(request));
        return true;
    }

    @PostMapping(Constants.SIGNUP_URL)
    public boolean signup(@RequestBody @Valid UserRq dto) {
        userService.create(dto);
        return true;
    }

    @GetMapping(Constants.WHOAMI_URL)
    public UserSecurityRs whoAmI(@AuthenticationPrincipal UserPrincipal principal) {
        final List<? extends GrantedAuthority> auths = principal.getAuthorities().stream().toList();
        final String role = auths.isEmpty()
                ? UserRole.ANONYMOUS.getAuthority()
                : auths.getFirst().getAuthority();
        return new UserSecurityRs(principal.getUsername(), role);
    }
}
