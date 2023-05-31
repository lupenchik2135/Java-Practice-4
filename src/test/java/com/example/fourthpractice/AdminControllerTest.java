package com.example.fourthpractice;

import com.example.fourthpractice.controllers.AdminController;
import com.example.fourthpractice.dao.UserDao;
import com.example.fourthpractice.messages.requests.AdminGetByAccountRequest;
import com.example.fourthpractice.messages.requests.AdminGetByUserRequest;
import com.example.fourthpractice.messages.responses.AdminGetAllTransactionsResponse;
import com.example.fourthpractice.messages.responses.AdminGetByAccountResponse;
import com.example.fourthpractice.messages.responses.AdminGetByUserResponse;
import com.example.fourthpractice.models.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
@Slf4j
class AdminControllerTest {

    @Autowired
    private AdminController adminController;

    @Autowired
    private UserDao userDao;

    @BeforeEach
    void loadContext(){
        UserModel admin = UserModel.fromEntity(userDao.getUserByEmail("admin6565@gmail.com"));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        admin.getEmail(), null, admin.getAuthorities())
        );
    }

    @org.junit.Test
    void getAllTransactionIsSuccess(){
        AdminGetAllTransactionsResponse allTransactionResponse = adminController.getAllTransactions();

        log.info("Get all transaction response: {}", allTransactionResponse);

        Assertions.assertNotNull(allTransactionResponse);
    }

    @Test
    void getAllUserTransactionIsSuccess(){
        AdminGetByUserRequest adminGetUserTransactionRequest =
                new AdminGetByUserRequest("test1@mail.ru");
        AdminGetByUserResponse allUserTransactionResponse = adminController
                .getAllUserTransactions(adminGetUserTransactionRequest);

        log.info("Get all user transaction response: {}", allUserTransactionResponse);

        Assertions.assertNotNull(allUserTransactionResponse);
    }

    @Test
    void getAllAccountTransactionIsSuccess(){
        AdminGetByAccountRequest adminGetAccountTransactionRequest =
                new AdminGetByAccountRequest(7);
        AdminGetByAccountResponse allAccountTransactionResponse = adminController
                .getAllAccountTransactions(adminGetAccountTransactionRequest);

        log.info("Get all account transaction response: {}", allAccountTransactionResponse);

        Assertions.assertNotNull(allAccountTransactionResponse);
    }
}
