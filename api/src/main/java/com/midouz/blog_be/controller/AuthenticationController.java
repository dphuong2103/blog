package com.midouz.blog_be.controller;

import com.midouz.blog_be.model.dto.AuthenticationResponseDTO;
import com.midouz.blog_be.model.request.CreateUserRequest;
import com.midouz.blog_be.model.request.LoginRequest;
import com.midouz.blog_be.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponseDTO register(@RequestBody CreateUserRequest request){
        return authenticationService.register(request);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponseDTO login(@RequestBody LoginRequest request){
        return authenticationService.login(request);
    }


}
