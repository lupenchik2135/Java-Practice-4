package com.example.fourthpractice.dao;

import com.example.fourthpractice.entities.UserEntity;
import com.example.fourthpractice.models.enums.UserRole;
import com.example.fourthpractice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final UserRepository userRepository;


    public boolean isUserExist(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    public UUID getUserIdByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow().getUserId();
    }

    public String getEmailById(UUID userId){
        return userRepository.findById(userId).orElseThrow().getEmail();
    }

    public UserEntity createUser(String email, String password, UserRole role){

        return userRepository.save(UserEntity.builder()
                .email(email)
                .password(password)
                .registerDate(LocalDate.now())
                .userRole(role)
                .build());

    }

    public UserEntity getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow();
    }

    public String getPasswordHash(String email){
        return userRepository.findByEmail(email).orElseThrow().getPassword();
    }
}
//    public void deleteUser(String email, String password){
//        if(isUserExist(email) && userRepository.findByEmail(email).get().getPassword() == password) {
//            userRepository.delete(userRepository.findByEmail(email).orElseThrow());
//        }
//    }