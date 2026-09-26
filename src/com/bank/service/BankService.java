package com.bank.service;

import java.sql.Connection;
import java.util.List;

import com.bank.dao.AccountDao;
import com.bank.dao.TransactionDAO;
import com.bank.dao.UserDAO;

import com.bank.dao.impl.AccountDAOImpl;
import com.bank.dao.impl.TransactionDAOImpl;
import com.bank.dao.impl.UserDAOImpl;

import com.bank.model.BankAccount;
import com.bank.model.Transaction;
import com.bank.model.User;

import com.bank.util.DBConnection;

public class BankService {

    private UserDAO userDAO =
            new UserDAOImpl();

    private AccountDao accountDAO =
            new AccountDAOImpl();

    private TransactionDAO transactionDAO =
            new TransactionDAOImpl();


    // REGISTER

    public boolean register(String name,
                            String email,
                            String password) {

        User user =
                new User(name, email, password);

        return userDAO.register(user);
    }


    // LOGIN

    public User login(String email,
                      String password) {

        return userDAO.login(email, password);
    }


    // CREATE ACCOUNT

    public boolean createAccount(
            int userId,
            String accountNumber) {

        BankAccount account =
                new BankAccount(
                        userId,
                        accountNumber);

        return accountDAO.createAccount(account);
    }


    // CHECK BALANCE

    public BankAccount getAccount(int userId) {

        return accountDAO.getAccountByUserId(
                userId);
    }


    // DEPOSIT

    public boolean deposit(int userId,
                           double amount) {

        if (amount <= 0) {
            return false;
        }

        try (Connection con =
                     DBConnection.getConnection()) {

            con.setAutoCommit(false);

            BankAccount account =
                    accountDAO.getAccountByUserId(
                            userId);

            if (account == null) {
                con.rollback();
                return false;
            }

            double newBalance =
                    account.getBalance()
                    + amount;

            boolean updated =
                    accountDAO.updateBalance(
                            con,
                            account.getAccountId(),
                            newBalance);

            if (!updated) {
                con.rollback();
                return false;
            }

            Transaction transaction =
                    new Transaction(
                            account.getAccountId(),
                            "DEPOSIT",
                            amount,
                            "Money deposited");

            boolean added =
                    transactionDAO.addTransaction(
                            con,
                            transaction);

            if (!added) {
                con.rollback();
                return false;
            }

            con.commit();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Deposit Error: "
                    + e.getMessage());

            return false;
        }
    }


    // WITHDRAW

    public boolean withdraw(int userId,
                            double amount) {

        if (amount <= 0) {
            return false;
        }

        try (Connection con =
                     DBConnection.getConnection()) {

            con.setAutoCommit(false);

            BankAccount account =
                    accountDAO.getAccountByUserId(
                            userId);

            if (account == null) {
                con.rollback();
                return false;
            }

            if (account.getBalance() < amount) {
                con.rollback();
                return false;
            }

            double newBalance =
                    account.getBalance()
                    - amount;

            boolean updated =
                    accountDAO.updateBalance(
                            con,
                            account.getAccountId(),
                            newBalance);

            if (!updated) {
                con.rollback();
                return false;
            }

            Transaction transaction =
                    new Transaction(
                            account.getAccountId(),
                            "WITHDRAW",
                            amount,
                            "Money withdrawn");

            boolean added =
                    transactionDAO.addTransaction(
                            con,
                            transaction);

            if (!added) {
                con.rollback();
                return false;
            }

            con.commit();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Withdraw Error: "
                    + e.getMessage());

            return false;
        }
    }


    // TRANSFER

    public boolean transfer(
            int userId,
            String receiverAccountNumber,
            double amount) {

        if (amount <= 0) {
            return false;
        }

        try (Connection con =
                     DBConnection.getConnection()) {

            con.setAutoCommit(false);

            BankAccount sender =
                    accountDAO.getAccountByUserId(
                            userId);

            BankAccount receiver =
                    accountDAO.getAccountByNumber(
                            receiverAccountNumber);

            if (sender == null ||
                receiver == null) {

                con.rollback();
                return false;
            }

            if (sender.getAccountId() ==
                receiver.getAccountId()) {

                con.rollback();
                return false;
            }

            if (sender.getBalance() < amount) {

                con.rollback();
                return false;
            }


            // Sender balance

            double senderBalance =
                    sender.getBalance()
                    - amount;

            boolean senderUpdated =
                    accountDAO.updateBalance(
                            con,
                            sender.getAccountId(),
                            senderBalance);

            if (!senderUpdated) {
                con.rollback();
                return false;
            }


            // Receiver balance

            double receiverBalance =
                    receiver.getBalance()
                    + amount;

            boolean receiverUpdated =
                    accountDAO.updateBalance(
                            con,
                            receiver.getAccountId(),
                            receiverBalance);

            if (!receiverUpdated) {
                con.rollback();
                return false;
            }


            // Sender transaction

            Transaction senderTransaction =
                    new Transaction(
                            sender.getAccountId(),
                            "TRANSFER",
                            amount,
                            "Transferred to "
                            + receiverAccountNumber);

            boolean senderTransactionAdded =
                    transactionDAO.addTransaction(
                            con,
                            senderTransaction);

            if (!senderTransactionAdded) {
                con.rollback();
                return false;
            }


            // Receiver transaction

            Transaction receiverTransaction =
                    new Transaction(
                            receiver.getAccountId(),
                            "RECEIVED",
                            amount,
                            "Received from "
                            + sender.getAccountNumber());

            boolean receiverTransactionAdded =
                    transactionDAO.addTransaction(
                            con,
                            receiverTransaction);

            if (!receiverTransactionAdded) {
                con.rollback();
                return false;
            }

            con.commit();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Transfer Error: "
                    + e.getMessage());

            return false;
        }
    }


    // TRANSACTION HISTORY

    public List<Transaction> getHistory(
            int userId) {

        BankAccount account =
                accountDAO.getAccountByUserId(
                        userId);

        if (account == null) {
            return null;
        }

        return transactionDAO.getTransactions(
                account.getAccountId());
    }
}