# Online Quiz & Assessment System (Java Swing)

## Project Overview

This project is a desktop-based quiz application developed using Java Swing and MySQL. It allows users to take multiple-choice quizzes with a timer, while admins can add questions dynamically. The system automatically evaluates answers and displays results with a leaderboard.

---

## Objectives

* Build an interactive quiz system
* Implement timer-based assessment
* Store questions and results in a database
* Automatically evaluate user responses
* Display performance using leaderboard

---

## Features

### User Module

* Enter username and start quiz
* Multiple-choice questions (MCQs)
* Timer-based quiz
* Auto score calculation
* Result display after completion
* Leaderboard ranking

### Admin Module

* Add new questions through GUI
* Store questions in MySQL database
* Easy management of quiz content

---

## Technologies Used

* Java (Swing) – GUI development
* MySQL – Database
* JDBC – Database connectivity

---

## Project Structure

```
QuizSystem/
│── DBConnection.java
│── Question.java
│── LoginUI.java
│── QuizUI.java
│── AdminUI.java
│── LeaderboardUI.java
│── MainApp.java
```

---

## Database Setup

```sql
CREATE DATABASE quiz_db;
USE quiz_db;

CREATE TABLE questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question TEXT,
    option1 VARCHAR(255),
    option2 VARCHAR(255),
    option3 VARCHAR(255),
    option4 VARCHAR(255),
    correct_option INT
);

CREATE TABLE results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    score INT
);
```

---

## How to Run the Project

1. Install Java (JDK 8 or above)
2. Install MySQL Server and Workbench
3. Create the database and tables using the SQL script
4. Import the project into Eclipse or IntelliJ
5. Add MySQL Connector (JDBC driver)
6. Update database credentials in DBConnection.java
7. Run MainApp.java

---

## How to Use

1. Open the application
2. Click Admin Panel and add questions
3. Enter username and start quiz
4. Answer questions within the time limit
5. View score and leaderboard

---

## Screenshots

Add screenshots of Login Page, Quiz UI, Admin Panel, and Leaderboard

---

## Future Enhancements

* User authentication system
* Negative marking feature
* Graphical performance analytics
* Web-based version using Servlet or React
* AI-based adaptive quizzes

---

## Author

Sonalika

---
