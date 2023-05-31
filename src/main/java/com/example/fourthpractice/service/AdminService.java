package com.example.fourthpractice.service;

import com.example.fourthpractice.entities.TransactionEntity;
import com.example.fourthpractice.messages.requests.AdminGetByAccountRequest;
import com.example.fourthpractice.messages.requests.AdminGetByUserRequest;
import com.example.fourthpractice.models.TransactionModel;

import java.util.List;

public interface AdminService {
    List<TransactionModel> findAllByUser_Email(AdminGetByUserRequest adminGetByUserRequest);
    List<TransactionModel> findAllByAccountFromAccountIdOrAccountWhereAccountId(AdminGetByAccountRequest adminGetByAccountRequest);
    List<TransactionModel> findAll();
}
