package com.example.fourthpractice.service;

import com.example.fourthpractice.dao.TransactionDao;
import com.example.fourthpractice.dao.UserDao;
import com.example.fourthpractice.entities.TransactionEntity;
import com.example.fourthpractice.messages.requests.AdminGetByAccountRequest;
import com.example.fourthpractice.messages.requests.AdminGetByUserRequest;
import com.example.fourthpractice.models.TransactionModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final TransactionDao transactionDao;
    private final UserDao userDao;
    @Override
    public List<TransactionModel> findAllByUser_Email(AdminGetByUserRequest adminGetByUserRequest) {
        return transactionDao.getAllTransactionsByEmail(userDao.getUserByEmail(adminGetByUserRequest.getUserEmail()));
    }

    @Override
    public List<TransactionModel> findAllByAccountFromAccountIdOrAccountWhereAccountId(AdminGetByAccountRequest adminGetByAccountRequest) {
        return transactionDao.getAllTransactionsByAccount(adminGetByAccountRequest.getAccountId(), adminGetByAccountRequest.getAccountId());
    }
    @Override
    public List<TransactionModel> findAll(){
        return transactionDao.getAllTransactions();
    }
}
