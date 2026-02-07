# ☕ Phase 1: JDBC Console Application

This is the foundational version of the Campus Placement System. It is a **Console-based Application** that connects directly to a MySQL database using the **DAO (Data Access Object)** design pattern.

## ✨ Features
* **Role-Based Access:** Separate menus for Admin and Students.
* **Admin Module:** Add/Update/Delete Drive details.
* **Student Module:** Register, Login, View Drives, Apply for Jobs.
* **Secure Configuration:** Database credentials are hidden using a `.properties` file.
* **Raw JDBC:** Demonstrates deep understanding of `Connection`, `PreparedStatement`, and `ResultSet`.

## ⚙️ Setup Instructions

### 1. Database Setup
Open your MySQL Workbench or Terminal and run these commands to create the necessary tables:

```sql
CREATE DATABASE campus_placement_db;
USE campus_placement_db;

-- 1. Admin Table
CREATE TABLE admin (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50)
);
INSERT INTO admin (username, password) VALUES ('admin', 'admin123');

-- 2. Companies Table
CREATE TABLE companies (
    company_id INT PRIMARY KEY AUTO_INCREMENT,
    company_name VARCHAR(100),
    job_role VARCHAR(100),
    package_lpa DECIMAL(10,2),
    arrival_date DATE,
    min_cgpa DECIMAL(4,2),
    UNIQUE(company_name, job_role)
);

-- 3. Students Table
CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    branch VARCHAR(50),
    cgpa DECIMAL(4,2),
    passout_year INT
);

-- 4. Applications Table
CREATE TABLE applications (
    app_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    company_id INT,
    status VARCHAR(50) DEFAULT 'Applied',
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (company_id) REFERENCES companies(company_id)
);