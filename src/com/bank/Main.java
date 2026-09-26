package com.bank;

import java.util.List;
import java.util.Scanner;

import com.bank.model.BankAccount;
import com.bank.model.Transaction;
import com.bank.model.User;
import com.bank.service.BankService;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static BankService service = new BankService();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n==============================");
            System.out.println("   BANKING MANAGEMENT SYSTEM");
            System.out.println("==============================");

            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    register();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    System.out.println(
                            "Thank you for using Banking System.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // REGISTER

    static void register() {

        System.out.println("\n--- USER REGISTRATION ---");

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        boolean result =
                service.register(name, email, password);

        if (result) {
            System.out.println(
                    "Registration successful!");
        } else {
            System.out.println(
                    "Registration failed!");
        }
    }

    // LOGIN

    static void login() {

        System.out.println("\n--- LOGIN ---");

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = service.login(email, password);

        if (user != null) {

            System.out.println(
                    "\nWelcome " + user.getName() + "!");

            accountMenu(user);

        } else {

            System.out.println(
                    "Invalid email or password.");
        }
    }

    // ACCOUNT MENU

    static void accountMenu(User user) {

        while (true) {

            System.out.println("\n==============================");
            System.out.println("        ACCOUNT MENU");
            System.out.println("==============================");

            System.out.println("1. Create Bank Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Money Transfer");
            System.out.println("6. Transaction History");
            System.out.println("7. Logout");

            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    createAccount(user);
                    break;

                case 2:
                    checkBalance(user);
                    break;

                case 3:
                    deposit(user);
                    break;

                case 4:
                    withdraw(user);
                    break;

                case 5:
                    transfer(user);
                    break;

                case 6:
                    history(user);
                    break;

                case 7:
                    System.out.println(
                            "Logged out successfully.");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // CREATE ACCOUNT

    static void createAccount(User user) {

        System.out.print(
                "Enter new account number: ");

        String accountNumber = sc.nextLine();

        boolean result =
                service.createAccount(
                        user.getUserId(),
                        accountNumber);

        if (result) {

            System.out.println(
                    "Bank account created successfully!");

        } else {

            System.out.println(
                    "Account creation failed!");
        }
    }

    // CHECK BALANCE

    static void checkBalance(User user) {

        BankAccount account =
                service.getAccount(user.getUserId());

        if (account == null) {

            System.out.println(
                    "No bank account found.");

            return;
        }

        System.out.println(
                "\nAccount Number : "
                + account.getAccountNumber());

        System.out.println(
                "Current Balance: ₹"
                + account.getBalance());
    }

    // DEPOSIT

    static void deposit(User user) {

        System.out.print(
                "Enter deposit amount: ");

        double amount = sc.nextDouble();

        boolean result =
                service.deposit(
                        user.getUserId(),
                        amount);

        if (result) {

            System.out.println(
                    "Amount deposited successfully!");

        } else {

            System.out.println(
                    "Deposit failed!");
        }
    }

    // WITHDRAW

    static void withdraw(User user) {

        System.out.print(
                "Enter withdrawal amount: ");

        double amount = sc.nextDouble();

        boolean result =
                service.withdraw(
                        user.getUserId(),
                        amount);

        if (result) {

            System.out.println(
                    "Amount withdrawn successfully!");

        } else {

            System.out.println(
                    "Withdrawal failed. Check balance.");
        }
    }

    // MONEY TRANSFER

    static void transfer(User user) {

        sc.nextLine();

        System.out.print(
                "Enter receiver account number: ");

        String receiver = sc.nextLine();

        System.out.print(
                "Enter amount: ");

        double amount = sc.nextDouble();

        boolean result =
                service.transfer(
                        user.getUserId(),
                        receiver,
                        amount);

        if (result) {

            System.out.println(
                    "Money transferred successfully!");

        } else {

            System.out.println(
                    "Transfer failed.");
        }
    }

    // TRANSACTION HISTORY

    static void history(User user) {

        List<Transaction> list =
                service.getHistory(
                        user.getUserId());

        if (list == null || list.isEmpty()) {

            System.out.println(
                    "No transactions found.");

            return;
        }

        System.out.println(
                "\n========= TRANSACTION HISTORY =========");

        for (Transaction t : list) {

            System.out.println(
                    "ID          : "
                    + t.getTransactionId());

            System.out.println(
                    "Type        : "
                    + t.getTransactionType());

            System.out.println(
                    "Amount      : ₹"
                    + t.getAmount());

            System.out.println(
                    "Description : "
                    + t.getDescription());

            System.out.println(
                    "Date        : "
                    + t.getTransactionDate());

            System.out.println(
                    "---------------------------------------");
        }
    }
}

