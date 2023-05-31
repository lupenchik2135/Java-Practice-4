package com.example.fourthpractice.dao;

import com.example.fourthpractice.entities.AccountEntity;
import com.example.fourthpractice.entities.TransactionEntity;
import com.example.fourthpractice.entities.UserEntity;
import com.example.fourthpractice.models.TransactionModel;
import com.example.fourthpractice.models.enums.UserRole;
import com.example.fourthpractice.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionDao {
    private final TransactionRepository transactionRepository;
    private final AccountDao accountDao;

    public TransactionEntity execute(float dealSize, UserEntity transactionOwner, AccountEntity accountFromWhereTransaction, AccountEntity accountWhereTransaction) {
        if(accountFromWhereTransaction.getMoney() - dealSize < 0){
            throw new IllegalArgumentException("Недостаточно средств на счете.");
        }
        accountDao.changeMoneyAmount(accountFromWhereTransaction.getAccountId(), accountFromWhereTransaction.getMoney() - dealSize);
        accountDao.changeMoneyAmount(accountWhereTransaction.getAccountId(), accountWhereTransaction.getMoney() + dealSize);
        return transactionRepository.save(TransactionEntity.builder()
                .dealSize(dealSize)
                .user(transactionOwner)
                .accountFrom(accountFromWhereTransaction)
                .accountWhere(accountWhereTransaction)
                .build()
        );

    }
    public List<TransactionModel> getAllTransactionsByEmail(UserEntity user){
        ArrayList<TransactionModel> transactionsList = new ArrayList<>();

        for (TransactionEntity transaction: transactionRepository.findAllByUser(user)){
            transactionsList.add(TransactionModel.fromEntity(transaction));
        }

        return transactionsList;
    }
    public List<TransactionModel> getAllTransactionsByAccount(int accountFromId, int accountWhereId){
        ArrayList<TransactionModel> transactionsList = new ArrayList<>();

        for (TransactionEntity transaction: transactionRepository.findAllByAccountFromAccountIdOrAccountWhereAccountId(accountFromId, accountWhereId)){
            transactionsList.add(TransactionModel.fromEntity(transaction));
        }

        return transactionsList;
    }
    public List<TransactionModel> getAllTransactions(){
        ArrayList<TransactionModel> transactionsList = new ArrayList<>();
        for (TransactionEntity transaction: transactionRepository.findAll()){
            transactionsList.add(TransactionModel.fromEntity(transaction));
        }
        return transactionsList;
    }
    public int deleteById(int transactionId){
        transactionRepository.deleteTransactionById(transactionId);
        return transactionId;
    }
}
