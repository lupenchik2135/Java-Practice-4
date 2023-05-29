package com.example.fourthpractice.messages.requests;

import com.example.fourthpractice.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestHeader;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateRequest {
    float money;
}
