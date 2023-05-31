package com.example.fourthpractice.models;

import com.example.fourthpractice.entities.AccountEntity;
import com.example.fourthpractice.entities.TransactionEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TransactionModel {
    float dealSize;
    String getOwnerTransactionEmail;
    int getTransactionId;

    public static TransactionModel fromEntity(TransactionEntity transactionEntity) {
        return new TransactionModel(
                transactionEntity.getDealSize(),
                transactionEntity.getUser().getEmail(),
                transactionEntity.getTransactionId()
        );
    }

}
