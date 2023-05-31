package com.example.fourthpractice.messages.responses;

import com.example.fourthpractice.models.TransactionModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminGetAllTransactionsResponse {
    List<TransactionModel> allTransactions;
}
