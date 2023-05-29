package com.example.fourthpractice.controllers;

import com.example.fourthpractice.messages.requests.UserDeleteRequest;
import com.example.fourthpractice.messages.requests.UserLoginRequest;
import com.example.fourthpractice.messages.requests.UserRegisterRequest;
import com.example.fourthpractice.messages.responses.UserDeleteResponse;
import com.example.fourthpractice.messages.responses.UserLoginResponse;
import com.example.fourthpractice.messages.responses.UserRegisterResponse;
import com.example.fourthpractice.models.TokenModel;
import com.example.fourthpractice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //http://localhost:8080/auth/register
    @PostMapping("/register")
    public UserRegisterResponse register(@RequestBody UserRegisterRequest registerRequest){
        TokenModel tokenModel = userService.register(registerRequest);
        return new UserRegisterResponse(
                tokenModel.getEmail(),
                tokenModel.getUserId(),
                tokenModel.getToken()
        );
    }

    //http://localhost:8080/auth/login
    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest loginRequest){
        log.info(loginRequest.toString());

        TokenModel tokenModel = userService.login(loginRequest);

        return new UserLoginResponse(tokenModel.getEmail(), tokenModel.getToken());
    }
    @DeleteMapping("/delete")
    public UserDeleteResponse delete(@RequestBody UserDeleteRequest deleteRequest){
        return new UserDeleteResponse(userService.delete(deleteRequest));
    }

}


