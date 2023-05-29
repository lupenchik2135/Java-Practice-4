package com.example.fourthpractice.service;

import com.example.fourthpractice.models.TokenModel;
import com.example.fourthpractice.models.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{
    private static final String ENC_KEY = "423F4528482B4D6251655368566D597133743677397A24432646294A404E6352";

    @Override
    public String generateToken(UserModel userModel) {
        return Jwts.builder()
                .signWith(getSignKey())
                .setSubject(userModel.getEmail())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(100)))
                .compact();
    }

    @Override
    public UserModel parseToken(String token) {
        return null;
    }
    private Key getSignKey(){
        byte[] keyBytes = Base64.getDecoder().decode(ENC_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}