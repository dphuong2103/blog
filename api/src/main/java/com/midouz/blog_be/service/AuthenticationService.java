package com.midouz.blog_be.service;

import com.midouz.blog_be.entity.User;
import com.midouz.blog_be.entity.UserToken;
import com.midouz.blog_be.model.dto.AuthenticationResponseDTO;
import com.midouz.blog_be.model.dto.UserDTO;
import com.midouz.blog_be.model.exception.DuplicateEmailException;
import com.midouz.blog_be.model.exception.UserNotFoundException;
import com.midouz.blog_be.model.request.CreateUserRequest;
import com.midouz.blog_be.model.request.LoginRequest;
import com.midouz.blog_be.repository.UserRepository;
import com.midouz.blog_be.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserTokenRepository userTokenRepository;
    public AuthenticationResponseDTO register(CreateUserRequest request) {
        if (isEmailExist(request.getEmail())) {
            throw new DuplicateEmailException(request.getEmail());
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = request.toUser(encodedPassword);
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        Instant now = Instant.now();
        UserToken userToken = UserToken
                .builder()
                .user(user)
                .tokenValue(refreshToken)
                .tokenType(UserToken.TokenType.REFRESH)
                .createdAt(now)
                .updatedAt(now)
                .expirationTime(jwtService.extractExpirationInstant(refreshToken))
                .build();
        userTokenRepository.save(userToken);
        return new AuthenticationResponseDTO(jwt, UserDTO.fromUser(user),refreshToken);
    }

    public AuthenticationResponseDTO login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(request.getEmail());
        }
        User user = optionalUser.get();
        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        Instant now = Instant.now();
        UserToken userToken = UserToken
                .builder()
                .user(user)
                .tokenValue(refreshToken)
                .tokenType(UserToken.TokenType.REFRESH)
                .createdAt(now)
                .updatedAt(now)
                .expirationTime(jwtService.extractExpirationInstant(refreshToken))
                .build();
        userTokenRepository.save(userToken);
        return new AuthenticationResponseDTO(jwt, UserDTO.fromUser(user), refreshToken);
    }

    private boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }
}
