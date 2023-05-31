package com.example.fourthpractice.service;

import com.example.fourthpractice.dao.AccountDao;
import com.example.fourthpractice.dao.TransactionDao;
import com.example.fourthpractice.dao.UserDao;
import com.example.fourthpractice.entities.AccountEntity;
import com.example.fourthpractice.entities.TransactionEntity;
import com.example.fourthpractice.messages.requests.TransactionDeleteRequest;
import com.example.fourthpractice.messages.requests.TransactionExecuteRequest;
import com.example.fourthpractice.models.AccountModel;
import com.example.fourthpractice.models.TransactionModel;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final TransactionDao transactionDao;
    private final AccountDao accountDao;
    private final JwtService jwtService;
    private final UserDao userDao;
    private final HttpServletRequest request;
    @Override
    public TransactionModel execute(TransactionExecuteRequest transactionExecuteRequest) {
        TransactionEntity newTransaction = transactionDao.execute(
                transactionExecuteRequest.getDealSize(),
                userDao.getUserByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()),
                accountDao.getAccountById(transactionExecuteRequest.getAccountFromWhereTransactionId()),
                accountDao.getAccountById(transactionExecuteRequest.getAccountWhereTransactionId())
        );
        return TransactionModel.fromEntity(newTransaction);
    }
//    @Override
//    public void deleteAccountId(){
//        transactionDao.deleteAccountId();
//    }
    @Override
    public int delete(TransactionDeleteRequest transactionDeleteRequest){
        return transactionDao.deleteById(transactionDeleteRequest.getTransactionId());
    }
    private String getTokenFromHeader() {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(header);
        if (ObjectUtils.isEmpty(header)) {
            return null;
        }
        return header;
    }
}
