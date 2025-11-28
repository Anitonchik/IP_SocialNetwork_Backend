package com.example.SocialNetwork.error;

public class NotFoundException extends RuntimeException {
    public <T> NotFoundException(Class<T> clazz, Long id) {
        super(String.format("%s with id %s is not found", clazz.getSimpleName(), id));
    }
}
