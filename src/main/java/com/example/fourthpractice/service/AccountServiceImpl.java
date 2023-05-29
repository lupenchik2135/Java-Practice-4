//package com.example.fourthpractice.service;
//
////import com.example.fourthpractice.dao.AccountDao;
////import com.example.fourthpractice.dao.UserDao;
////import com.example.fourthpractice.entities.AccountEntity;
////import com.example.fourthpractice.entities.UserEntity;
////import com.example.fourthpractice.messages.requests.AccountCreateRequest;
////import com.example.fourthpractice.messages.requests.UserRegisterRequest;
////import com.example.fourthpractice.models.AccountModel;
////import com.example.fourthpractice.models.TokenModel;
////import com.example.fourthpractice.models.enums.UserRole;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class AccountServiceImpl implements AccountService{
//    private final AccountDao accountDao;
//    private final JwtService jwtService;
//    private final UserDao userService;
//    @Override
//    public AccountModel create(AccountCreateRequest accountCreateRequest) {
//
//        AccountEntity newAccount = accountDao.createAccount(
//                accountCreateRequest.getMoney(),
//                userService.getUserById(jwtService.parseToken(accountCreateRequest.getToken()).getUserId())
//        );
//        return new AccountModel(
//                newAccount.getMoney(),
//                newAccount.getAccountOwner(),
//                newAccount.getAccountId()
//        );
//    }
//}
