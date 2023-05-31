package com.example.fourthpractice;

import com.example.fourthpractice.controllers.AccountController;
import com.example.fourthpractice.controllers.UserController;
import com.example.fourthpractice.messages.requests.*;
import com.example.fourthpractice.messages.responses.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountControllerTest {


    @Autowired
    private AccountController accountController;

    @Test
    @Order(1)
    void createAccount(){
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "account@gmail.com", null, null)
        );
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        AccountCreateRequest accountCreateRequest = new AccountCreateRequest(4040);
        AccountCreateResponse accountCreateResponse = accountController.create(accountCreateRequest);
        Assertions.assertTrue(accountCreateResponse != null);
    }
    @Test
    @Order(2)
    void infoAccount(){

        AccountInfoRequest accountInfoRequest = new AccountInfoRequest(57);
        AccountInfoResponse accountInfoResponse = accountController.getInfo(accountInfoRequest);
        Assertions.assertTrue(accountInfoResponse != null);
    }
    @Test
    @Order(3)
    void deleteAccount(){
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "account@gmail.com", null, null)
        );
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        AccountDeleteRequest accountDeleteRequest = new AccountDeleteRequest(58);
        AccountDeleteResponse accountDeleteResponse = accountController.deleteAccount(accountDeleteRequest);
        Assertions.assertTrue(accountDeleteResponse != null);
    }


}
