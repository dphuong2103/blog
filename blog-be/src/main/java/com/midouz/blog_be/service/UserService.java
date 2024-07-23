package com.midouz.blog_be.service;

import com.midouz.blog_be.entity.User;
import com.midouz.blog_be.model.dto.UserDTO;
import com.midouz.blog_be.model.exception.DuplicateEmailException;
import com.midouz.blog_be.model.exception.UserNotFoundException;
import com.midouz.blog_be.model.request.CreateUserRequest;
import com.midouz.blog_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO register(CreateUserRequest request){
        User user = request.toUser();
        if(isEmailExist(user.getEmail())){
            throw new DuplicateEmailException(request.getEmail());
        }
        userRepository.save(user);
        return UserDTO.fromUser(user);
    }

    public UserDTO getUserById(String userId){
        return userRepository.findById(userId)
                .map(UserDTO::fromUser)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    private boolean isEmailExist(String email){
        return userRepository.existsByEmail(email);
    }
}
