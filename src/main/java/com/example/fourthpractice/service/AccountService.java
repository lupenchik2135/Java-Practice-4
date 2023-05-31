package com.example.fourthpractice.service;

import com.example.fourthpractice.messages.requests.AccountCreateRequest;
import com.example.fourthpractice.messages.requests.AccountDeleteRequest;
import com.example.fourthpractice.messages.requests.AccountInfoRequest;
import com.example.fourthpractice.models.AccountModel;

public interface AccountService {
    AccountModel create(AccountCreateRequest accountCreateRequest);

    AccountModel findAccount(AccountInfoRequest accountInfoRequest);

    AccountModel deleteAccount(AccountDeleteRequest accountDeleteRequest);
}
