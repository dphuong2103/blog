package com.midouz.blog_be.controller;

import com.midouz.blog_be.model.dto.UserDTO;
import com.midouz.blog_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable String userId){
        return userService.getUserById(userId);
    }
}
