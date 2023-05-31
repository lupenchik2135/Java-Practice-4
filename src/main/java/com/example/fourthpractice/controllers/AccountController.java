package com.example.fourthpractice.controllers;

import com.example.fourthpractice.dao.UserDao;
import com.example.fourthpractice.messages.requests.*;
import com.example.fourthpractice.messages.responses.*;
import com.example.fourthpractice.models.AccountModel;
import com.example.fourthpractice.models.TokenModel;
import com.example.fourthpractice.service.AccountService;
import com.example.fourthpractice.service.JwtService;
import com.example.fourthpractice.service.TransactionService;
import com.example.fourthpractice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    //http://localhost:8080/account/create
    @PostMapping("/create")
    public AccountCreateResponse create(@RequestBody AccountCreateRequest accountCreateRequest) {

        AccountModel accountModel = accountService.create(accountCreateRequest);
        log.info(accountCreateRequest.toString());
        return new AccountCreateResponse(
                accountModel.getMoney(),
                accountModel.getAccountId(),
                accountModel.getOwnerAccountEmail()
        );
    }

    @GetMapping("/info")
    public AccountInfoResponse getInfo(@RequestBody AccountInfoRequest accountInfoRequest) {
        log.info(accountInfoRequest.toString());
        AccountModel accountModel = accountService.findAccount(accountInfoRequest);
        return new AccountInfoResponse(
                accountModel.getMoney(),
                accountModel.getAccountId()
        );
    }

    @DeleteMapping("/delete")
    public AccountDeleteResponse deleteAccount(@RequestBody AccountDeleteRequest accountDeleteRequest) {
        log.info(accountDeleteRequest.toString());
        return new AccountDeleteResponse(accountService.deleteAccount(accountDeleteRequest).getAccountId());
    }

}