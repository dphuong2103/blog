package com.midouz.blog_be.model.exception;

public class TagNotFoundException extends RuntimeException{
    public TagNotFoundException(String message) {
        super(message);
    }
}
