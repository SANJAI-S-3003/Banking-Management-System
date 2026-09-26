package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bank.dao.UserDAO;
import com.bank.model.User;
import com.bank.util.DBConnection;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean register(User user) {

        String sql =
                "INSERT INTO users(name, email, password) " +
                "VALUES (?, ?, ?)";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            int rows = ps.executeUpdate();

            con.close();

            return rows > 0;

        } catch (Exception e) {

            System.out.println(
                    "Registration Error: "
                    + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public User login(String email, String password) {

        String sql =
                "SELECT * FROM users " +
                "WHERE email = ? AND password = ?";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                );

                con.close();

                return user;
            }

            con.close();

        } catch (Exception e) {

            System.out.println(
                    "Login Error: "
                    + e.getMessage()
            );
        }

        return null;
    }
}