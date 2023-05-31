package com.example.fourthpractice;

import com.example.fourthpractice.controllers.UserController;
import com.example.fourthpractice.messages.requests.UserLoginRequest;
import com.example.fourthpractice.messages.requests.UserRegisterRequest;
import com.example.fourthpractice.messages.responses.UserDeleteResponse;
import com.example.fourthpractice.messages.responses.UserLoginResponse;
import com.example.fourthpractice.messages.responses.UserRegisterResponse;
import com.example.fourthpractice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {
    @Autowired
    private UserController userController;
    private static String email;
    private static String password;
    void loadContext(){
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "aboba@gmail.com", null, null)
        );}
    @BeforeAll
    static void initTestingObjects(){
        email = "aboba@gmail.com";
        password = "Kuritsa666";
    }
    @Test
    @Order(1)
    void registerTest(){
        UserRegisterRequest registerRequest = new UserRegisterRequest(password, email);
        UserRegisterResponse userRegisterResponse = userController.register(registerRequest);
        Assertions.assertTrue(userRegisterResponse != null);
    }
    @Test
    @Order(2)
    void loginTest(){
        UserLoginRequest loginRequest = new UserLoginRequest(password, email);
        UserLoginResponse userLoginResponse = userController.login(loginRequest);
        Assertions.assertTrue(userLoginResponse != null);
    }
    @Test
    @Order(3)
    void deleteTest(){
        loadContext();
        UserDeleteResponse userDeleteResponse = userController.delete();
        log.info("Delete response: {}", userDeleteResponse);
        Assertions.assertTrue(userDeleteResponse != null);
    }
}
