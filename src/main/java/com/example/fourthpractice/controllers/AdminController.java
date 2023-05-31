package com.example.fourthpractice.controllers;

import com.example.fourthpractice.dao.UserDao;
import com.example.fourthpractice.entities.TransactionEntity;
import com.example.fourthpractice.messages.requests.AccountInfoRequest;
import com.example.fourthpractice.messages.requests.AdminGetByAccountRequest;
import com.example.fourthpractice.messages.requests.AdminGetByUserRequest;
import com.example.fourthpractice.messages.requests.TransactionExecuteRequest;
import com.example.fourthpractice.messages.responses.*;
import com.example.fourthpractice.models.AccountModel;
import com.example.fourthpractice.models.TransactionModel;
import com.example.fourthpractice.repositories.TransactionRepository;
import com.example.fourthpractice.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final UserDao userDao;
    //http://localhost:8080/admin/byUser
    @GetMapping("/byUser")
    public AdminGetByUserResponse getAllUserTransactions(@RequestBody AdminGetByUserRequest adminGetByUserRequest) {
        log.info(adminGetByUserRequest.toString());
        List<TransactionModel> allTransactions = adminService.findAllByUser_Email(adminGetByUserRequest);
        return new AdminGetByUserResponse(
                allTransactions
        );
    }
    //http://localhost:8080/admin/byAccount
    @GetMapping("/byAccount")
    public AdminGetByAccountResponse getAllAccountTransactions(@RequestBody AdminGetByAccountRequest adminGetByAccountRequest) {
        log.info(adminGetByAccountRequest.toString());
        List<TransactionModel> allTransactions = adminService.findAllByAccountFromAccountIdOrAccountWhereAccountId(adminGetByAccountRequest);
        return new AdminGetByAccountResponse(
                allTransactions
        );
    }
    @GetMapping("/all")
    public AdminGetAllTransactionsResponse getAllTransactions(){
        List<TransactionModel> allTransactions = adminService.findAll();
        return new AdminGetAllTransactionsResponse(
                allTransactions
        );
    }
}
