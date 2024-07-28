package com.midouz.blog_be.controller;

import com.midouz.blog_be.model.dto.UserDTO;
import com.midouz.blog_be.service.JwtService;
import com.midouz.blog_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable String userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/info")
    public UserDTO getUserInfo(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid token");
        }
        String jwt = authHeader.substring(7);
        String userId = jwtService.extractUserId(jwt);
        if (userId == null) {
            throw new RuntimeException("Invalid token");
        }
        return userService.getUserById(userId);
    }
}
