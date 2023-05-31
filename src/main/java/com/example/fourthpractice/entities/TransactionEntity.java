package com.example.fourthpractice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transactions")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    private float dealSize;
//    @ManyToOne(targetEntity = AccountEntity.class)
//    private AccountEntity accountFromWhereTransaction;
//
//    @ManyToOne(targetEntity = UserEntity.class)
//    private UserEntity transactionOwner;
//
//    @ManyToOne(targetEntity = AccountEntity.class)
//    private AccountEntity accountWhereTransaction;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "transactions_from_account_id")
        private AccountEntity accountFrom;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "transactions_to_account_id")
        private AccountEntity accountWhere;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private UserEntity user;

}
