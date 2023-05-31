package com.example.fourthpractice.entities;

import com.example.fourthpractice.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID userId;

    private String email;

    private LocalDate registerDate;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @OneToMany(mappedBy = "accountOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountEntity> accounts = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionEntity> transactions = new ArrayList<>();
//    @OneToMany
//    @JoinColumn(name = "account_owner_user_id")
//    private List<AccountEntity> ownedAccounts;
//
//    @OneToMany
//    @JoinColumn(name = "transaction_owner_user_id")
//    private List<TransactionEntity> ownedTransactions;
}   

