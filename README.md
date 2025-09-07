# 📚 IT3003 - Student Database Mini Project

## 📌 Project Overview
A simple **Student Database Management System** built with **Java (JDBC + MySQL)**.  

It supports full **CRUD operations** and extra features like:  
- 🔍 **Search by name**  
- 🆔 **View student by ID**  
- ✅ **Input validation** (email, phone, date)  
- ⚙️ **Externalized DB config** (`db.properties`)

---

## 🛠️ Tech Stack
- **Language:** Java 17 (works with Java 8+)  
- **Database:** MySQL (via XAMPP)  
- **Build Tool:** Maven  
- **JDBC Driver:** mysql-connector-java 

---

## 📂 Project Structure
```
IT3003-Student-DB-Mini-Project/
│── pom.xml
│── README.md
│── .gitignore
│── src/
│ └── main/
│ ├── java/com/example/studentdb/
│ │ ├── db/DatabaseConnection.java
│ │ ├── model/Student.java
│ │ ├── dao/StudentDAO.java
│ │ └── main/StudentApp.java
│ └── resources/db.properties
```

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

## 📥 Clone the Repository
```bash
    git clone https://github.com/JanudiAdhikari/IT3003-Student-DB-Mini-Project.git
    cd IT3003-Student-DB-Mini-Project

---

## ▶️ Running the Project
### Method 1: IntelliJ IDEA
1. Open the project in IntelliJ.
2. Let Maven download dependencies (pom.xml).
3. Run StudentApp (main class).

### Method 2: Command Line
```bash
    mvn compile
    mvn exec:java -Dexec.mainClass="com.example.studentdb.main.StudentApp"

---

## 📖 Features
### Core CRUD
- ➕ **Add Student** → Insert new student.  
- 📋 **View All Students** → Display all records.  
- ✏️ **Update Student** → Modify existing student by ID.  
- ❌ **Delete Student** → Remove student by ID.  

### Bonus Features
- 🔍 **Search by Name** → Find students with partial name match.  
- 🆔 **View Student by ID** → Fetch one student using ID.  
- ✅ **Input Validation**  
  - Email format check  
  - Phone number check (10 digits)  
  - Date format check (`yyyy-mm-dd`)  
- ⚙️ **Externalized Config** → DB connection settings in `db.properties`.  

---

## 📸 Sample Menu
===== Student Database Menu =====
1. Add Student
2. View All Students
3. Update Student
4. Delete Student
5. Search Student by Name
6. View Student by ID
7. Exit

---

## ⚙️ Assumptions
- Default XAMPP credentials:  
  - User: `root`  
  - Password: *(empty)*  
- Database name: `studentdb`  
- MySQL server: `localhost:3306`