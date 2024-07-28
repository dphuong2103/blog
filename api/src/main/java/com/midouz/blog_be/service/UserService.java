package com.midouz.blog_be.service;

import com.midouz.blog_be.model.dto.UserDTO;
import com.midouz.blog_be.model.exception.UserNotFoundException;
import com.midouz.blog_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserDTO getUserById(String userId){
        return userRepository.findById(userId)
                .map(UserDTO::fromUser)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
