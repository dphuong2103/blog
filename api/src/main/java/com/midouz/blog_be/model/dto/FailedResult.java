package com.midouz.blog_be.model.dto;

public class FailedResult<T> extends Result<T> {
    public FailedResult(String message) {
        super(false, message, null);
    }
}
