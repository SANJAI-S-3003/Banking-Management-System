package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bank.dao.AccountDao;
import com.bank.model.BankAccount;
import com.bank.util.DBConnection;

public class AccountDAOImpl implements AccountDao {

    @Override
    public boolean createAccount(BankAccount account) {

        String sql =
                "INSERT INTO accounts " +
                "(user_id, account_number, balance) " +
                "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, account.getUserId());
            ps.setString(2, account.getAccountNumber());
            ps.setDouble(3, 0);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Account Creation Error: "
                    + e.getMessage());

            return false;
        }
    }

    @Override
    public BankAccount getAccountByUserId(int userId) {

        String sql =
                "SELECT * FROM accounts WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new BankAccount(
                        rs.getInt("account_id"),
                        rs.getInt("user_id"),
                        rs.getString("account_number"),
                        rs.getDouble("balance")
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Account Search Error: "
                    + e.getMessage());
        }

        return null;
    }

    @Override
    public BankAccount getAccountByNumber(
            String accountNumber) {

        String sql =
                "SELECT * FROM accounts " +
                "WHERE account_number = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new BankAccount(
                        rs.getInt("account_id"),
                        rs.getInt("user_id"),
                        rs.getString("account_number"),
                        rs.getDouble("balance")
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Account Search Error: "
                    + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean updateBalance(Connection con,
                                 int accountId,
                                 double balance) {

        String sql =
                "UPDATE accounts SET balance = ? " +
                "WHERE account_id = ?";

        try (PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setDouble(1, balance);
            ps.setInt(2, accountId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Balance Update Error: "
                    + e.getMessage());

            return false;
        }
    }
}