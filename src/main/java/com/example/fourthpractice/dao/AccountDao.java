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

    public AccountEntity createAccount(float money, UserEntity accountOwner){
        return accountRepository.save(AccountEntity.builder()
                .money(money)
                .accountOwner(accountOwner)
                .build());

    }
    public boolean isAccountExist(UserEntity accountOwner){
        return accountRepository.findByAccountOwner(accountOwner).isPresent();
    }
    public void deleteAccount(UserEntity accountOwner, UUID accountId){
        if(isAccountExist(accountOwner) && accountRepository.findByAccountOwner(accountOwner).get().getAccountId() == accountId) {
            accountRepository.delete(accountRepository.findByAccountOwner(accountOwner).orElseThrow());
        }
    }
    public AccountEntity getAccountByUser(UserEntity accountOwner){
        return accountRepository.findByAccountOwner(accountOwner).orElseThrow();
    }

}
