package com.bank.dao;

import java.sql.Connection;
import java.util.List;

import com.bank.model.Transaction;

public interface TransactionDAO {

    boolean addTransaction(Connection con,
                           Transaction transaction);

    List<Transaction> getTransactions(int accountId);
}