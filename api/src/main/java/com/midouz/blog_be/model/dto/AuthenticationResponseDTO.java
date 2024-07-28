package com.midouz.blog_be.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponseDTO {
    private String jwt;
    private UserDTO user;

}

