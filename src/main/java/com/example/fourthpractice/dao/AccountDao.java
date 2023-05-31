package com.example.fourthpractice.dao;

import com.example.fourthpractice.entities.AccountEntity;
import com.example.fourthpractice.entities.UserEntity;
import com.example.fourthpractice.models.UserModel;
import com.example.fourthpractice.models.enums.UserRole;
import com.example.fourthpractice.repositories.AccountRepository;
import com.example.fourthpractice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccountDao {
    private final AccountRepository accountRepository;

    public AccountEntity createAccount(float money, UserEntity accountOwner) {
        return accountRepository.save(AccountEntity.builder()
                .money(money)
                .accountOwner(accountOwner)
                .build());

    }

    public boolean isAccountExist(int accountId, UserEntity accountOwner) {
        return accountRepository.findByAccountIdAndAccountOwner(accountId, accountOwner).isPresent();
    }

    public void deleteAccount(int accountId) {
       accountRepository.deleteAccountEntitiesByAccountId(accountId);
    }

    public AccountEntity getAccountById(int accountId) {
        return accountRepository.findByAccountId(accountId).orElseThrow();
    }
    public void changeMoneyAmount(int accountId, float money){
        accountRepository.changeMoneyAmount(accountId, money);
    };
    public AccountEntity getAccountByIdAndOwner(int accountId, UserEntity accountOwner) {
        return accountRepository.findByAccountIdAndAccountOwner(accountId, accountOwner).orElseThrow();
    }

}
