package com.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/banking_system";

    private static final String USER = "root";

    private static final String PASSWORD = "root";

    public static Connection getConnection() {

        try {

            Connection con =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            System.out.println("Database Connected Successfully!");

            return con;

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}