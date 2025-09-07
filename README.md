# IT3003 - Student Database Mini Project

## 📌 Project Overview
This is a simple **Student Database Management System** built with **Java (JDBC + MySQL)**.  
It supports basic **CRUD operations** (Create, Read, Update, Delete) and additional features like **search by name**, **input validation**, and **transaction handling**.

---

## 🛠️ Tech Stack
- **Language:** Java 17  
- **Database:** MySQL (XAMPP)  
- **Build Tool:** Maven  
- **JDBC Driver:** mysql-connector-java  

---

## 📂 Project Structure
IT3003-Student-DB-Mini-Project/
│── pom.xml
│── README.md
│── src/
│ └── main/
│ ├── java/
│ │ └── com/example/studentdb/
│ │ ├── db/DatabaseConnection.java
│ │ ├── model/Student.java
│ │ ├── dao/StudentDAO.java
│ │ └── main/StudentApp.java
│ └── resources/

---

## 🗄️ Database Setup
1. Start **XAMPP** and ensure MySQL is running.  
2. Open **phpMyAdmin** or MySQL CLI.  
3. Create the database:
   ```sql
   CREATE DATABASE studentdb;
   USE studentdb;

4. Create the students table:
   ```sql
    CREATE TABLE IF NOT EXISTS students (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        email VARCHAR(100) NOT NULL UNIQUE,
        phone VARCHAR(20),
        dob DATE,
        address TEXT,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

---

## ▶️ Running the Project
Method 1: IntelliJ IDEA
1. Open the project in IntelliJ.
2. Build using Maven (mvn compile).
3. Run StudentApp (main class).

Method 2: Command Line
mvn compile
mvn exec:java -Dexec.mainClass="com.example.studentdb.main.StudentApp"

---

## 📖 Features
Core CRUD
Add Student → Insert new student.
View All Students → Display all records.
Update Student → Modify existing student by ID.
Delete Student → Remove student by ID.

Bonus Features
Search by Name → Find students using partial name match.
Input Validation
Checks email format before inserting/updating.
Validates date format (yyyy-mm-dd).

---

## 📸 Sample Menu
===== Student Database Menu =====
1. Add Student
2. View All Students
3. Update Student
4. Delete Student
5. Search Student by Name
6. Exit

---

## ⚙️ Assumptions
Default XAMPP credentials:
    User: root
    Password: (empty)
Database name: studentdb.
MySQL is running on localhost:3306.