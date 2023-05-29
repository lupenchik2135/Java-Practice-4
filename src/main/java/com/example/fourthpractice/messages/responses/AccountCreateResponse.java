package com.example.fourthpractice.messages.responses;

import com.example.fourthpractice.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateResponse {
    private float money;
    private UUID accountId;
    private UserEntity accountOwner;
}
