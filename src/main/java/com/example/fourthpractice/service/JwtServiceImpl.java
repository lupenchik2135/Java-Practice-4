package com.example.fourthpractice.service;

import com.example.fourthpractice.models.TokenModel;
import com.example.fourthpractice.models.UserModel;
import com.example.fourthpractice.models.enums.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{
    private static final String ENC_KEY = "423F4528482B4D6251655368566D597133743677397A24432646294A404E6352";

    @Override
    public String generateToken(UserModel userModel) {
        return Jwts.builder()
                .signWith(getSigninKey())
                .setSubject(userModel.getEmail())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(600)))
                .claim("userId", userModel.getUserId().toString())
                .claim("userRole", userModel.getUserRole())
                .compact();
    }

    @Override
    public UserModel parseToken(String jwt) {
        Jwt token = Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parse(jwt);

        Claims body = (Claims) token.getBody();
        System.out.println(body);

        return UserModel.builder()
                .email(body.getSubject())
                .userId(UUID.fromString(body.get("userId", String.class)))
                .userRole(UserRole.valueOf(body.get("userRole", String.class)))
                .build();
    }
    private Key getSigninKey() {
        byte[] keyBytes = Base64.getDecoder().decode(ENC_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}