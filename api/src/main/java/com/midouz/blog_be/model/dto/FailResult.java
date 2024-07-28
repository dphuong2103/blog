package com.midouz.blog_be.model.dto;

public class FailResult extends Result<Void> {
    public FailResult(String message) {
        super(false, message, null);
    }
}
