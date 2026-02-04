# College Management System - Code Documentation

This document provides a detailed explanation of the **College Management System** project. It is designed to help you understand the code structure, the logic behind each component, and the Java concepts applied. Use this guide to explain the project to your professor or classmates.

---

## 1. Project Overview

**Project Name:** College Management System  
**Type:** Console-based Java Application  
**Purpose:** To manage college entities like Students, Teachers, and Classrooms.  
**Key Features:**
*   **CRUD Operations:** Add, View, Update, and Delete records for Students, Teachers, and Classes.
*   **Association:** Assign teachers to classes and enroll students in classes.
*   **Data Persistence:** Saves data to text files (`students.txt`, `teachers.txt`, `classrooms.txt`) so data is not lost when the program closes.
*   **Input Validation:** Ensures user inputs (like age, ID) are strictly validated before processing.

---

## 2. Project Structure (Packages)

The code is organized into **packages** to keep it modular and clean.

| Package | Description | Files |
| :--- | :--- | :--- |
| `com.college.model` | Contains the data classes (the "nouns" of the system). | `Person.java`, `Student.java`, `Teacher.java`, `Classroom.java` |
| `com.college.interfaces` | Contains interfaces that define contracts for classes. | `Manageable.java` |
| `com.college.util` | Contains utility/helper classes for specific tasks. | `FileHandler.java`, `InputValidator.java` |
| `com.college.main` | Contains the main execution logic. | `CollegeManagementSystem.java` |

---

## 3. Detailed Code Explanation

Here is a breakdown of every file in the project.

### A. The Models (`com.college.model`)

These classes represent the real-world objects in our system.

#### 1. `Person.java` (Abstract Class)
*   **Role:** The parent class for `Student` and `Teacher`.
*   **Key Concept: Inheritance:** Use to avoid code duplication. Since both students and teachers have an `id` and a `name`, we put these in a common parent class.
*   **Key Concept: Abstraction:** It is defined as `abstract` because we never want to create just a "Person" object; we only create specific types of persons (Student or Teacher).
*   **Key Code:**
    *   `protected int id;`: The `protected` keyword means subclasses can access this field directly.
    *   `public abstract String getDetails();`: An abstract method. It forces every child class to define *how* they show their details.

#### 2. `Student.java` (Child Class)
*   **Role:** Represents a student.
*   **Key Concept: Inheritance:** `extends Person`. It inherits `id` and `name` from the `Person` class.
*   **Fields:** Adds `age` and `course` which are attributes specific to students.
*   **Methods:**
    *   **Constructor:** Calls `super(id, name)` to initialize the parent fields, then initializes its own `age` and `course`.
    *   `getDetails()`: Overrides the parent method to return a string formatted specifically for students (e.g., "Student [ID=1, Name=John...]").

#### 3. `Teacher.java` (Child Class)
*   **Role:** Represents a teacher.
*   **Key Concept: Inheritance:** `extends Person`.
*   **Fields:** Adds `subject` (specific to teachers).
*   **Methods:**
    *   `getDetails()`: Returns specific teacher details like "Teacher [ID=2, Name=Mr. Smith, Subject=Math]".

#### 4. `Classroom.java`
*   **Role:** Represents a class (e.g., "Math 101").
*   **Key Concept: Composition:** A classroom *has a* Teacher and *has a list of* Students. It binds the other model objects together.
*   **Key Concept: Collections:** Uses `ArrayList<Student> students` to store a flexible number of students.
*   **Key Code:**
    *   `addStudent(Student s)`: Adds a student object to the internal list.
    *   `removeStudent(int id)`: Uses a lambda expression `s -> s.getId() == id` to find and remove a student by their unique ID.

---

### B. The Interfaces (`com.college.interfaces`)

#### 1. `Manageable.java`
*   **Role:** Defines a contract. Any class that implements this interface MUST behave in a "manageable" way (i.e., it must be able to return its ID and info).
*   **Why use it?** It guarantees consistency. If we added a `Laboratory` class later, we could implement `Manageable` to ensure it works with any system that expects "manageable" items.
*   **Methods:** `getId()` and `getInfo()`.

---

### C. The Utilities (`com.college.util`)

#### 1. `InputValidator.java`
*   **Role:** Checks if data entered by the user is correct to prevent crashes or bad data.
*   **Key Concept: Static Methods:** We use `static` methods (like `validateAge`) so we don't have to create an `InputValidator` object every time. We just call `InputValidator.validateAge(20)`.
*   **Logic:**
    *   `validateName`: Checks if the string is not null and not empty (`!name.trim().isEmpty()`).
    *   `validateAge`: Strictly checks if `age >= 16 && age <= 100`.

#### 2. `FileHandler.java`
*   **Role:** Handles saving to and loading from text files.
*   **Key Concept: File I/O:**
    *   **Saving (`saveData`):** detailed use of `PrintWriter` and `FileWriter` to write comma-separated values (CSV) like `1,John,20,CS`.
    *   **Loading (`loadData`):** detailed use of `BufferedReader` and `FileReader` to read lines, split them by commas (`,`), and recreate the objects.
*   **Key Concept: Exception Handling:** Uses `try-catch` blocks to safely handle errors (e.g., if a file is missing or locked).

---

### D. The Main Application (`com.college.main`)

#### 1. `CollegeManagementSystem.java`
*   **Role:** The "Brain" of the operation. It contains the `main` method where the program starts.
*   **Key Structure:**
    *   `Scanner`: Reads user input from the console.
    *   `ArrayList`s: `static ArrayList<Student>`, `teachers`, `classrooms` act as the database in memory while the program runs.
    *   `main()`: Runs a `while(true)` loop to keep showing the menu until "Exit" is chosen.
    *   `switch(choice)`: Decides which function to call based on user input.

*   **Key Functions (CRUD):** 
    Each entity (Student, Teacher, Class) has functions like:
    *   `add...()`: Performs validation checks -> Creates Object -> Adds to List.
    *   `view...()`: Asks for ID -> Finds Object -> Prints Object.
    *   `update...()`: Asks for ID -> Finds Object -> Uses Setters to change specific values.
    *   `delete...()`: Asks for ID -> Removes from List.

---

## 4. Key Concepts to Mention to Your Professor

When explaining the code, try to use these technical terms to demonstrate your understanding:

1.  **Object-Oriented Programming (OOP):**
    *   "I used **Inheritance** for `Student` and `Teacher` to avoid code duplication."
    *   "I used **Polymorphism** in `getDetails()` so that calling the same method on different objects produces the correct behavior for each."
    *   "I used **Encapsulation** by making fields `private` and providing public Getters/Setters to protect the data."

2.  **Collections Framework:**
    *   "I used `ArrayList` because it behaves like a dynamic array—we can add or remove students without needing to define a fixed size in advance."

3.  **Exception Handling:**
    *   "I used `try-catch` blocks to prevent the program from crashing if the user enters letters instead of numbers, or if the data files are missing during startup."

4.  **Data Persistence:**
    *   "The system features persistent storage. It saves data to text files on exit and loads it on startup, ensuring data is retained between sessions."

---

## 5. How to Run

1.  **Compile:** Java needs to turn code into bytecode.
    *   Double-click `build.bat` (if on Windows).
2.  **Run:**
    *   Double-click `run.bat`.

---
