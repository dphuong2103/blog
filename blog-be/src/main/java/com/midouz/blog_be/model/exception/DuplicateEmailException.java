package com.midouz.blog_be.model.exception;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String email){
        super("Email " + email + " is already taken.");
    }
}
