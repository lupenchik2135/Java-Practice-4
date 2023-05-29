package com.example.fourthpractice.service;

import com.example.fourthpractice.models.UserModel;

public interface JwtService {
    public String generateToken(UserModel userModel);
    public UserModel parseToken(String token);
}