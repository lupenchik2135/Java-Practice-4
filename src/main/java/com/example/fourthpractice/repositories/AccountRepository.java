package com.example.fourthpractice.repositories;

import com.example.fourthpractice.entities.AccountEntity;
import com.example.fourthpractice.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
//    Optional<AccountEntity> findByUser(UserEntity accountOwner);
//    Optional<AccountEntity> findByTransactionId(String transactionId);
}
