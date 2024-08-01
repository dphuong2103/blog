package com.midouz.blog_be.model.exception;

public class UserTokenNotFoundException extends RuntimeException{
    public UserTokenNotFoundException(String refreshToken) {
        super("User token with refresh token " + refreshToken + " not found");
    }
}
