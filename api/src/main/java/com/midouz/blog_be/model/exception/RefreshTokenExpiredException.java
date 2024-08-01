package com.midouz.blog_be.model.exception;

public class RefreshTokenExpiredException extends RuntimeException{
    public RefreshTokenExpiredException(String refreshToken) {
        super("Refresh token: " + refreshToken + " expired");
    }
}
