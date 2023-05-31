package com.example.fourthpractice.messages.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionExecuteResponse {
    float dealSize;
    private String transactionOwnerEmail;
    private int transactionId;

}
