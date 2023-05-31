package com.example.fourthpractice;

import com.example.fourthpractice.entities.UserEntity;
import com.example.fourthpractice.models.UserModel;
import com.example.fourthpractice.models.enums.UserRole;
import com.example.fourthpractice.repositories.AccountRepository;
import com.example.fourthpractice.repositories.TransactionRepository;
import com.example.fourthpractice.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer {


    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private void generate() {
        log.info("DataInitializer post construct");
        createUser();
    }

    private void createUser() {
        if(!userRepository.findByEmail("admin6565@gmail.com").isPresent()){
            userRepository.save(
                    UserEntity.builder()
                            .email("admin6565@gmail.com")
                            .password(passwordEncoder.encode("password"))
                            .userRole(UserRole.ADMIN)
                            .registerDate(LocalDate.now()).build()
            );
        }
    }
}