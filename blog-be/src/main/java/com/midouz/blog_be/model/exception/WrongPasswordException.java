package com.midouz.blog_be.model.exception;

public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException(){
        super("Email or password incorrect!");
    }
}
