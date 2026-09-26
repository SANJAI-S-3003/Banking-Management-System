package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bank.dao.TransactionDAO;
import com.bank.model.Transaction;
import com.bank.util.DBConnection;

public class TransactionDAOImpl
        implements TransactionDAO {

    @Override
    public boolean addTransaction(
            Connection con,
            Transaction transaction) {

        String sql =
                "INSERT INTO transactions " +
                "(account_id, transaction_type, amount, description) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(
                    1,
                    transaction.getAccountId());

            ps.setString(
                    2,
                    transaction.getTransactionType());

            ps.setDouble(
                    3,
                    transaction.getAmount());

            ps.setString(
                    4,
                    transaction.getDescription());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Transaction Error: "
                    + e.getMessage());

            return false;
        }
    }

    @Override
    public List<Transaction> getTransactions(
            int accountId) {

        List<Transaction> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM transactions " +
                "WHERE account_id = ? " +
                "ORDER BY transaction_date DESC";

        try (Connection con =
                     DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Transaction t =
                        new Transaction();

                t.setTransactionId(
                        rs.getInt("transaction_id"));

                t.setAccountId(
                        rs.getInt("account_id"));

                t.setTransactionType(
                        rs.getString(
                                "transaction_type"));

                t.setAmount(
                        rs.getDouble("amount"));

                t.setDescription(
                        rs.getString("description"));

                t.setTransactionDate(
                        rs.getTimestamp(
                                "transaction_date"));

                list.add(t);
            }

        } catch (Exception e) {

            System.out.println(
                    "History Error: "
                    + e.getMessage());
        }

        return list;
    }
}