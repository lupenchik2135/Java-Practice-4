package com.example.fourthpractice;

import com.example.fourthpractice.controllers.AccountController;
import com.example.fourthpractice.controllers.TransactionController;
import com.example.fourthpractice.dao.AccountDao;
import com.example.fourthpractice.messages.requests.AccountCreateRequest;
import com.example.fourthpractice.messages.requests.TransactionExecuteRequest;
import com.example.fourthpractice.messages.responses.AccountCreateResponse;
import com.example.fourthpractice.messages.responses.TransactionExecuteResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
@Slf4j
class TransactionControllerTest {

    @Autowired
    private TransactionController transactionController;

    @Autowired
    private AccountController accountController;

    @Autowired
    private AccountDao accountDao;

    @BeforeAll
    static void loadContext(){
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "account@gmail.com", null, null)
        );
    }

    @Test
    void isMoneyExchange(){
        AccountCreateRequest accountRegisterRequest1 = new AccountCreateRequest(1000);
        AccountCreateResponse accountRegisterResponse1 = accountController.create(accountRegisterRequest1);

        float account1BalanceBefore = accountDao.getAccountById(accountRegisterResponse1.getAccountId()).getMoney();

        AccountCreateRequest accountRegisterRequest2 = new AccountCreateRequest(0);
        AccountCreateResponse accountRegisterResponse2 = accountController.create(accountRegisterRequest2);

        float account2BalanceBefore = accountDao.getAccountById(accountRegisterResponse2.getAccountId()).getMoney();

        TransactionExecuteRequest transactionRegisterRequest = new TransactionExecuteRequest(100,
                accountRegisterResponse1.getAccountId(), accountRegisterResponse2.getAccountId());

        TransactionExecuteResponse transactionRegisterResponse = transactionController.register(transactionRegisterRequest);

        float account1BalanceAfter = accountDao.getAccountById(accountRegisterResponse1.getAccountId()).getMoney();
        float account2BalanceAfter = accountDao.getAccountById(accountRegisterResponse2.getAccountId()).getMoney();

        log.info("Register response: {}", transactionRegisterResponse);

        Assertions.assertNotNull(transactionRegisterResponse);

        Assertions.assertEquals(account1BalanceBefore,
                account1BalanceAfter + transactionRegisterRequest.getDealSize());

        Assertions.assertEquals(account2BalanceBefore,
                    account2BalanceAfter - transactionRegisterRequest.getDealSize());

    }
}