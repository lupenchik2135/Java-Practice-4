package com.example.fourthpractice.repositories;

import com.example.fourthpractice.entities.AccountEntity;
import com.example.fourthpractice.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
    Optional<AccountEntity> findByAccountId(int accountId);
    Optional<AccountEntity> findByAccountIdAndAccountOwner(int accountId, UserEntity accountOwner);
    @Transactional
    @Modifying
    @Query("UPDATE AccountEntity e " +
            "SET e.money = :newMoneyAmount " +
            "WHERE e.accountId = :accountId")
    void changeMoneyAmount(int accountId, float newMoneyAmount);

    @Transactional
    @Modifying
    @Query("DELETE FROM AccountEntity e " +
            "WHERE e.accountId = :accountId " )
    void deleteAccountEntitiesByAccountId(int accountId);
}
