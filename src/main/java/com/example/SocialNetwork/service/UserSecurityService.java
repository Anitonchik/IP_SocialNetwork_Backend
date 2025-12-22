package com.example.SocialNetwork.service;

import com.example.SocialNetwork.entity.UserEntity;
import com.example.SocialNetwork.error.NotFoundException;
import com.example.SocialNetwork.repository.UserRepository;

import com.example.SocialNetwork.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSecurityService implements UserDetailsService {
    final private UserRepository repository;

    public UserSecurityService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public UserEntity getEntityByLogin(String login) {
        return repository.findByUserNameIgnoreCase(login)
                .orElseThrow(() -> new NotFoundException(UserEntity.class, "login", login));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            final UserEntity existsUser = getEntityByLogin(username);
            return new UserPrincipal(existsUser);
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
