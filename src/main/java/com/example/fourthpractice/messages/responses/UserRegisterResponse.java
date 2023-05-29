package com.example.fourthpractice.messages.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponse {
    private String email;
    private UUID userId;
    private String token;
}
