package com.bank.model;

public class BankAccount {

    private int accountId;
    private int userId;
    private String accountNumber;
    private double balance;

    public BankAccount() {
    }

    public BankAccount(int userId, String accountNumber) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public BankAccount(int accountId, int userId,
                       String accountNumber, double balance) {
        this.accountId = accountId;
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}