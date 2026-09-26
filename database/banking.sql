-- ============================================
-- Banking Management System
-- Database Setup Script
-- ============================================

CREATE DATABASE IF NOT EXISTS banking_management;

USE banking_management;

-- ============================================
-- Users Table
-- ============================================

CREATE TABLE IF NOT EXISTS users (
user_id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100) NOT NULL,
email VARCHAR(150) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL
);

-- ============================================
-- Accounts Table
-- ============================================

CREATE TABLE IF NOT EXISTS accounts (
account_id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT NOT NULL,
account_number VARCHAR(50) NOT NULL UNIQUE,
balance DECIMAL(15,2) NOT NULL DEFAULT 0.00,

```
CONSTRAINT fk_accounts_user
    FOREIGN KEY (user_id)
    REFERENCES users(user_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
```

);

-- ============================================
-- Transactions Table
-- ============================================

CREATE TABLE IF NOT EXISTS transactions (
transaction_id INT PRIMARY KEY AUTO_INCREMENT,
account_id INT NOT NULL,
transaction_type VARCHAR(30) NOT NULL,
amount DECIMAL(15,2) NOT NULL,
description VARCHAR(255),
transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

```
CONSTRAINT fk_transactions_account
    FOREIGN KEY (account_id)
    REFERENCES accounts(account_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
```

);

-- ============================================
-- Optional: View table structures
-- ============================================

DESCRIBE users;
DESCRIBE accounts;
DESCRIBE transactions;
