package com.example.fourthpractice.service;

import com.example.fourthpractice.dao.AccountDao;
import com.example.fourthpractice.dao.UserDao;
import com.example.fourthpractice.entities.AccountEntity;
import com.example.fourthpractice.entities.UserEntity;
import com.example.fourthpractice.messages.requests.AccountCreateRequest;
import com.example.fourthpractice.messages.requests.AccountDeleteRequest;
import com.example.fourthpractice.messages.requests.AccountInfoRequest;
import com.example.fourthpractice.messages.requests.UserRegisterRequest;
import com.example.fourthpractice.models.AccountModel;
import com.example.fourthpractice.models.TokenModel;
import com.example.fourthpractice.models.UserModel;
import com.example.fourthpractice.models.enums.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;
    private final JwtService jwtService;
    private final UserDao userDao;
    private final HttpServletRequest request;

    @Override
    public AccountModel create(AccountCreateRequest accountCreateRequest) {
        log.info(SecurityContextHolder.getContext().getAuthentication().getName());
        AccountEntity newAccount = accountDao.createAccount(
                accountCreateRequest.getMoney(),
                userDao.getUserByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        );
        return AccountModel.fromEntity(newAccount);
    }

    public AccountModel findAccount(AccountInfoRequest accountInfoRequest) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return AccountModel.fromEntity(accountDao.getAccountByIdAndOwner(accountInfoRequest.getAccountId(), userDao.getUserByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal())));
    }
 @Override
    public AccountModel deleteAccount(AccountDeleteRequest accountDeleteRequest) {
     log.info(SecurityContextHolder.getContext().getAuthentication().getName());
        if(userDao.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()) != null){
            System.out.println(accountDeleteRequest.getAccountId());
            accountDao.deleteAccount(accountDeleteRequest.getAccountId());
        }
        return AccountModel.builder().accountId(accountDeleteRequest.getAccountId()).build();

    }


}
