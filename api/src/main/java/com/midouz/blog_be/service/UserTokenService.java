package com.midouz.blog_be.service;

import com.midouz.blog_be.entity.User;
import com.midouz.blog_be.entity.UserToken;
import com.midouz.blog_be.model.dto.AuthenticationResponseDTO;
import com.midouz.blog_be.model.dto.UserDTO;
import com.midouz.blog_be.model.exception.RefreshTokenExpiredException;
import com.midouz.blog_be.model.exception.UserTokenNotFoundException;
import com.midouz.blog_be.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserTokenService {
    private final UserTokenRepository userTokenRepository;
    private final JwtService jwtService;
    public AuthenticationResponseDTO refreshAccessToken(String refreshToken) {
        UserToken userToken = userTokenRepository.findUserTokenByTokenValue(refreshToken).orElseThrow(()-> new UserTokenNotFoundException(refreshToken));
        if(userToken.getExpirationTime().compareTo(Instant.now()) < 0){
            throw new RefreshTokenExpiredException(userToken.getTokenValue());
        }
        User user = userToken.getUser();
        String accessToken = jwtService.generateToken(user);
        return new AuthenticationResponseDTO(accessToken, UserDTO.fromUser(user), refreshToken);
    }
}
