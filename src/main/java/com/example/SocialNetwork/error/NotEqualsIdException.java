package com.example.SocialNetwork.error;

public class NotEqualsIdException extends RuntimeException {
    public NotEqualsIdException(Long firstExpectedId, Long secondExpectedId, Long providedId) {
        super(String.format("%s or %s expected ids not equals %s id", firstExpectedId, secondExpectedId, providedId));
    }
}
