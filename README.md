\# 🏦 Banking Management System



A console-based Banking Management System developed using \*\*Java, JDBC, and MySQL\*\*. The project follows a layered architecture with separate model, DAO, service, and database utility components.



\## 📌 Project Overview



The Banking Management System provides basic banking operations through a Java console application connected to a MySQL database using JDBC.



The project demonstrates practical implementation of:



\* Java Object-Oriented Programming

\* JDBC database connectivity

\* CRUD operations

\* DAO design pattern

\* Layered application architecture

\* MySQL database operations

\* Exception handling

\* Transaction management



\## 🛠️ Technologies Used



| Technology  | Purpose                 |

| ----------- | ----------------------- |

| Java        | Application development |

| JDBC        | Database connectivity   |

| MySQL       | Data storage            |

| Eclipse IDE | Development             |

| Git         | Version control         |

| GitHub      | Source code management  |



\## ✨ Features



\* 👤 User registration

\* 🔐 User login

\* 🏦 Bank account management

\* 💰 Deposit operations

\* 💸 Withdrawal operations

\* 💳 Balance management

\* 📋 Transaction management

\* 🗄️ MySQL database integration



\## 🏗️ Project Structure



```text

Banking-Management-System/

│

├── src/

│   └── com/

│       └── bank/

│           ├── Main.java

│           │

│           ├── dao/

│           │   ├── AccountDao.java

│           │   ├── TransactionDAO.java

│           │   ├── UserDAO.java

│           │   │

│           │   └── impl/

│           │       ├── AccountDAOImpl.java

│           │       ├── TransactionDAOImpl.java

│           │       └── UserDAOImpl.java

│           │

│           ├── model/

│           │   ├── BankAccount.java

│           │   ├── Transaction.java

│           │   └── User.java

│           │

│           ├── service/

│           │   └── BankService.java

│           │

│           └── util/

│               └── DBConnection.java

│

├── .gitignore

└── README.md

```



\## 🧩 Architecture



The application is organized into multiple layers:



\### Model Layer



Contains Java classes representing application data:



\* `User`

\* `BankAccount`

\* `Transaction`



\### DAO Layer



Handles database operations:



\* `UserDAO`

\* `AccountDao`

\* `TransactionDAO`



The implementation classes contain the actual JDBC database operations.



\### Service Layer



`BankService` handles the application's business logic and coordinates operations between the application and DAO layer.



\### Utility Layer



`DBConnection` is responsible for establishing the connection between the Java application and MySQL.



\## 🔄 Application Flow



```text

User

&#x20;│

&#x20;▼

Main.java

&#x20;│

&#x20;▼

BankService

&#x20;│

&#x20;▼

DAO Layer

&#x20;│

&#x20;▼

JDBC

&#x20;│

&#x20;▼

MySQL Database

```



\## 🗄️ Database



The application uses \*\*MySQL\*\* as its database.



Before running the project:



1\. Install MySQL.

2\. Create the required database.

3\. Create the required tables.

4\. Update the database credentials in `DBConnection.java`.

5\. Add the MySQL JDBC driver to the project.



Example JDBC configuration:



```java

String url = "jdbc:mysql://localhost:3306/your\_database";

String username = "root";

String password = "your\_password";

```



> ⚠️ Do not commit your actual MySQL password or other sensitive credentials to GitHub.



\## ▶️ How to Run



\### 1. Clone the repository



```bash

git clone https://github.com/SANJAI-S-3003/Banking-Management-System.git

```



\### 2. Open the project



Open the project in \*\*Eclipse IDE\*\*.



\### 3. Configure MySQL



Create the required database and tables.



\### 4. Configure JDBC



Make sure the MySQL JDBC driver is available in the project.



\### 5. Configure database connection



Update the database URL, username, and password in:



```text

src/com/bank/util/DBConnection.java

```



\### 6. Run the application



Run:



```text

src/com/bank/Main.java

```



\## 📚 Concepts Demonstrated



This project demonstrates practical knowledge of:



\* Java OOP

\* Classes and Objects

\* Encapsulation

\* Interfaces

\* Exception Handling

\* Collections

\* JDBC

\* SQL

\* CRUD Operations

\* DAO Pattern

\* Layered Architecture

\* MySQL Database Connectivity



\## 🎯 Learning Objective



The main objective of this project is to understand how a Java application communicates with a relational database using JDBC and how application logic can be organized using a layered architecture.



\## 👨‍💻 Author



\*\*Sanjai S\*\*



GitHub: \[SANJAI-S-3003](https://github.com/SANJAI-S-3003)



\---



⭐ If you find this project useful, feel free to explore the repository.



