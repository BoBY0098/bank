package com.bankSystem.service;

import com.bankSystem.entity.BankAccount;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class Bank {

    private ConcurrentHashMap<Long, BankAccount> accounts = new ConcurrentHashMap<>();
    private ReentrantLock lock = new ReentrantLock();

    public BankAccount createAccount(String accountHolderName, double initialBalance) {
        BankAccount account = new BankAccount(accountHolderName, initialBalance);
        accounts.put(account.getAccountNumber(), account);
        return account;
    }

    public void deposit(Long accountNumber, double amount) {
        lock.lock();
        try {
            BankAccount account = accounts.get(accountNumber);
            account.setBalance(account.getBalance() + amount);
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(Long accountNumber, double amount) {
        lock.lock();
        try {
            BankAccount account = accounts.get(accountNumber);
            account.setBalance(account.getBalance() - amount);
        } finally {
            lock.unlock();
        }
    }

    public void transfer(Long fromAccount, Long toAccount, double amount) {
        lock.lock();
        try {
            withdraw(fromAccount, amount); deposit(toAccount, amount);
        } finally {
            lock.unlock();
        }
    }

    public double getBalance(Long accountNumber) {
        return accounts.get(accountNumber).getBalance();
    }
}
