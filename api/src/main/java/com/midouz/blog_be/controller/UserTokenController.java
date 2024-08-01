package com.midouz.blog_be.controller;

import com.midouz.blog_be.constant.API;
import com.midouz.blog_be.model.dto.AuthenticationResponseDTO;
import com.midouz.blog_be.service.UserTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping(API.API_PREFIX + "user-tokens")
@RequiredArgsConstructor
public class UserTokenController {
    private final UserTokenService userTokenService;

    @PostMapping("/refresh-access-token")
    public ResponseEntity<AuthenticationResponseDTO> refreshAccessToken(@RequestHeader(value = "refreshToken", required = false) Optional<String> optionalRefreshToken){
        return optionalRefreshToken.map(s -> ResponseEntity.ok(userTokenService.refreshAccessToken(s))).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
