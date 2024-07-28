package com.midouz.blog_be.model.dto;


import com.midouz.blog_be.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Instant createdTime;
    private Instant lastLoggingTime;

    public static UserDTO fromUser(User user){
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdTime(user.getCreatedTime())
                .lastLoggingTime(user.getLastLoggingTime())
                .build();
    }
}
