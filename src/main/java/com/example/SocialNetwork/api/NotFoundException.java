package com.example.SocialNetwork.api;

public class NotFoundException extends RuntimeException {
    public <T> NotFoundException(Class<T> clazz, int id) {
        super(String.format("%s with id %s is not found", clazz.getSimpleName(), id));
    }
}
