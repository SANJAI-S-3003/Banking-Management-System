package com.bank.dao;

import java.sql.Connection;

import com.bank.model.BankAccount;

public interface AccountDao {

    boolean createAccount(BankAccount account);

    BankAccount getAccountByUserId(int userId);

    BankAccount getAccountByNumber(String accountNumber);

    boolean updateBalance(Connection con,
                          int accountId,
                          double balance);
}