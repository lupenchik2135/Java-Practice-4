//package com.example.fourthpractice.controllers;
//
//import com.example.fourthpractice.dao.UserDao;
//import com.example.fourthpractice.messages.requests.AccountCreateRequest;
//import com.example.fourthpractice.messages.requests.UserLoginRequest;
//import com.example.fourthpractice.messages.requests.UserRegisterRequest;
//import com.example.fourthpractice.messages.responses.AccountCreateResponse;
//import com.example.fourthpractice.messages.responses.UserLoginResponse;
//import com.example.fourthpractice.messages.responses.UserRegisterResponse;
//import com.example.fourthpractice.models.AccountModel;
//import com.example.fourthpractice.models.TokenModel;
//import com.example.fourthpractice.service.AccountService;
//import com.example.fourthpractice.service.JwtService;
//import com.example.fourthpractice.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@Slf4j
//@RequestMapping("/account")
//@RequiredArgsConstructor
//public class AccountController {
//
//    private final AccountService accountService;
//    private final JwtService jwtService;
//
//    //http://localhost:8080/account/create
//   @PostMapping("/create")
//    public AccountCreateResponse create(@RequestBody AccountCreateRequest accountCreateRequest){
//
//       AccountModel accountModel = accountService.create(accountCreateRequest);
//        return new AccountCreateResponse(
//                accountModel.getMoney(),
//                accountModel.getAccountId(),
//                accountModel.getOwnerAccount()
//        );
//    }
//
//    //http://localhost:8080/auth/login
////    @PostMapping("/login")
////    public UserLoginResponse login(@RequestBody UserLoginRequest loginRequest){
////        log.info(loginRequest.toString());
////
////        TokenModel tokenModel = userService.login(loginRequest);
////
////        return new UserLoginResponse(tokenModel.getEmail(), tokenModel.getToken());
////    }
//
//}
