package com.example.fourthpractice.service;

import com.example.fourthpractice.messages.requests.AccountCreateRequest;
import com.example.fourthpractice.messages.requests.TransactionDeleteRequest;
import com.example.fourthpractice.messages.requests.TransactionExecuteRequest;
import com.example.fourthpractice.models.AccountModel;
import com.example.fourthpractice.models.TransactionModel;

public interface TransactionService {
    TransactionModel execute(TransactionExecuteRequest transactionExecuteRequest);
    int delete(TransactionDeleteRequest transactionDeleteRequest);

}
