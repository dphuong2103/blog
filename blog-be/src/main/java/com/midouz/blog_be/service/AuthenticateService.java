package com.midouz.blog_be.service;

import com.midouz.blog_be.entity.User;
import com.midouz.blog_be.model.dto.UserDTO;
import com.midouz.blog_be.model.exception.WrongPasswordException;
import com.midouz.blog_be.model.request.LoginRequest;
import com.midouz.blog_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticateService {
    private final UserRepository userRepository;

    public UserDTO login(LoginRequest request){
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        if(optionalUser.isEmpty()){
            throw new WrongPasswordException();
        }
        return UserDTO.fromUser(optionalUser.get());
    }
}
