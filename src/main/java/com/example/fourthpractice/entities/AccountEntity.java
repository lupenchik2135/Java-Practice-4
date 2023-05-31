package com.example.fourthpractice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    private float money;

//    @ManyToOne(targetEntity = UserEntity.class)
//    private UserEntity accountOwner;
//
//    @OneToMany
//    @JoinColumn(name = "account_where_transaction_account_id")
//    private List<TransactionEntity> transferredTransaction;
//
//    @OneToMany
//    @JoinColumn(name = "account_from_where_transaction_account_id")
//    private List<TransactionEntity> receivedTransaction;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity accountOwner;

    @OneToMany(mappedBy = "accountFrom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionEntity> transactionsFrom = new ArrayList<>();
    @OneToMany(mappedBy = "accountWhere", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionEntity> transactionsWhere = new ArrayList<>();
}