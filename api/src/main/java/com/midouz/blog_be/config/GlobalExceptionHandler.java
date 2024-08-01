package com.midouz.blog_be.config;

import com.midouz.blog_be.model.dto.FailedResult;
import com.midouz.blog_be.model.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<FailedResult<Void>> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.badRequest().body(new FailedResult<>(e.getMessage()));
    }

    @ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<FailedResult<Void>> handleBlogNotFoundException(BlogNotFoundException e) {
        return ResponseEntity.badRequest().body(new FailedResult<>(e.getMessage()));
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<FailedResult<Void>> handleDuplicateEmailException(DuplicateEmailException e) {
        return ResponseEntity.badRequest().body(new FailedResult<>(e.getMessage()));
    }

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<FailedResult<Void>> handleTagNotFoundException(TagNotFoundException e) {
        return ResponseEntity.badRequest().body(new FailedResult<>(e.getMessage()));
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<FailedResult<Void>> handleWrongPasswordException(WrongPasswordException e) {
        return ResponseEntity.badRequest().body(new FailedResult<>(e.getMessage()));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<FailedResult<Void>> handleExpiredJwtException(ExpiredJwtException e) {
        return ResponseEntity.status(401).body(new FailedResult<>(e.getMessage()));
    }

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<FailedResult<Void>> handleRefreshTokenExpiredException(RefreshTokenExpiredException e) {
        return ResponseEntity.status(401).body(new FailedResult<>(e.getMessage()));
    }
}
