package com.example.fourthpractice.repositories;

import com.example.fourthpractice.entities.AccountEntity;
import com.example.fourthpractice.entities.TransactionEntity;
import com.example.fourthpractice.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findAllByUser(UserEntity user);
    List<TransactionEntity> findAllByAccountFromAccountIdOrAccountWhereAccountId(int accountFromId, int accountWhereId);
    Optional<TransactionEntity> findByTransactionId(int accountId);
    @Transactional
    @Modifying
    @Query("DELETE FROM TransactionEntity e " +
            "WHERE e.transactionId = :transactionId ")
    void deleteTransactionById(int transactionId);

}
