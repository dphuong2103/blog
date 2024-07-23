package com.midouz.blog_be.model.request;

import com.midouz.blog_be.entity.User;
import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class CreateUserRequest {
    @Nonnull
    private String firstName;
    @Nonnull
    private String lastName;
    @Nonnull
    private String email;
    @Nonnull
    private String password;

    public User toUser(){
        Instant now = Instant.now();
        return User.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .password(this.password)
                .createdTime(now)
                .lastLoggingTime(now)
                .build();
    }
}
