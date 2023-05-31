package com.example.fourthpractice.messages.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionExecuteRequest {
    float dealSize;
    int accountFromWhereTransactionId;
    int accountWhereTransactionId;
}
