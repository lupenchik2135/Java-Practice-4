package com.example.fourthpractice.service;

import com.example.fourthpractice.messages.requests.UserDeleteRequest;
import com.example.fourthpractice.messages.requests.UserLoginRequest;
import com.example.fourthpractice.messages.requests.UserRegisterRequest;
import com.example.fourthpractice.models.TokenModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    TokenModel register(UserRegisterRequest registerRequest);

    TokenModel login(UserLoginRequest loginRequest);
    String delete(UserDeleteRequest userDeleteRequest);

}