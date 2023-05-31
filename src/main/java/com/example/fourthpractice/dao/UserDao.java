package com.example.fourthpractice.dao;

import com.example.fourthpractice.entities.UserEntity;
import com.example.fourthpractice.models.enums.UserRole;
import com.example.fourthpractice.repositories.AccountRepository;
import com.example.fourthpractice.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;


    public boolean isUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public UUID getUserIdByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow().getUserId();
    }

    public String getEmailById(UUID userId) {
        return userRepository.findById(userId).orElseThrow().getEmail();
    }
    @Transactional
    public UserEntity createUser(String email, String password, UserRole role) {

        return userRepository.save(UserEntity.builder()
                .email(email)
                .password(password)
                .registerDate(LocalDate.now())
                .userRole(role)
                .build());

    }

@Transactional
    public void  deleteById(UUID userId){

        userRepository.deleteById(userId);
    }
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public UserEntity getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public String getPasswordHash(String email) {
        return userRepository.findByEmail(email).orElseThrow().getPassword();
    }

}