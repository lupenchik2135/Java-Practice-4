package com.example.fourthpractice.dao;

import com.example.fourthpractice.entities.UserEntity;
import com.example.fourthpractice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final UserRepository userRepository;


    public UUID getUserIdByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow().getUserId();
    }

    public String getEmailById(UUID userId){
        return userRepository.findById(userId).orElseThrow().getEmail();
    }
    public UserEntity createUser(String email, String password){
        return userRepository
                .save(UserEntity.builder()
                        .email(email)
                        .password(password)
                        .registerDate(LocalDate.now())
                        .build());
    }
}