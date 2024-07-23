package com.midouz.blog_be.controller;

import com.midouz.blog_be.model.dto.UserDTO;
import com.midouz.blog_be.model.request.CreateUserRequest;
import com.midouz.blog_be.model.request.LoginRequest;
import com.midouz.blog_be.service.AuthenticateService;
import com.midouz.blog_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/authenticate")
@RequiredArgsConstructor
public class AuthenticateController {
    private final UserService userService;
    private final AuthenticateService authenticateService;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO register(@RequestBody CreateUserRequest request){
        return userService.register(request);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO login(@RequestBody LoginRequest request){
        return authenticateService.login(request);
    }
}
