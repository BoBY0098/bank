package com.bankSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bank-accounts")
@Data
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;

    private String accountHolderName;

    private double balance;

    public BankAccount() {
    }

    public BankAccount(String accountHolderName, double balance) {
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }
}
