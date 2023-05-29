//package com.example.fourthpractice.models;
//
//import com.example.fourthpractice.entities.AccountEntity;
//import com.example.fourthpractice.entities.UserEntity;
//import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Collection;
//import java.util.UUID;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@Builder
//public class AccountModel {
//    float money;
//    UserEntity ownerAccount;
//    UUID accountId;
//
//    public static AccountModel fromEntity(AccountEntity accountEntity) {
//        return new AccountModel(
//                accountEntity.getMoney(),
//                accountEntity.getAccountOwner(),
//                accountEntity.getAccountId()
//        );
//    }
//
//    public float getMoney() {
//        return money;
//    }
//    public UUID getAccountId() {return accountId; }
//    public UUID getOwnerAccountId() { return ownerAccount.getUserId(); }
//}
//
//
//
