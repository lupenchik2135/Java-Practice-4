package com.example.fourthpractice.service;

import com.example.fourthpractice.models.UserModel;

public interface JwtService {
    String generateToken(UserModel userModel);

    UserModel parseToken(String token);
}