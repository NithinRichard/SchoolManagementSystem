package com.college.main;

import com.college.util.FileHandler;
import com.college.util.InputValidator;
import com.college.model.*;
import java.util.ArrayList; // Import ArrayList collection
import java.util.Scanner; // Import Scanner for user input

/**
 * MAIN CLASS: CollegeManagementSystem
 * 
 * This is the main class that runs the College Management System program.
 * It provides a menu-driven console interface for managing students, teachers,
 * and classes.
 * 
 * CONCEPT DEMONSTRATED: MAIN METHOD
 * - Every Java program must have a main method as the entry point
 * - public static void main(String[] args) is where program execution starts
 * 
 * CONCEPT DEMONSTRATED: STATIC VARIABLES AND METHODS
 * - Static variables belong to the class, not individual objects
 * - Static methods can be called without creating an object
 */
public class CollegeManagementSystem {

    // STATIC VARIABLES - belong to the class
    // These lists store all the data for students, teachers, and classrooms
    // CONCEPT DEMONSTRATED: COLLECTIONS (ArrayList)
    // - ArrayList is a dynamic array that can grow/shrink
    // - We use ArrayList to store multiple objects of the same type
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Teacher> teachers = new ArrayList<>();
    private static ArrayList<Classroom> classrooms = new ArrayList<>();

    // Scanner object for reading user input from console
    // CONCEPT DEMONSTRATED: INPUT/OUTPUT (Scanner)
    // - Scanner reads input from keyboard
    // - System.in means input from standard input (keyboard)
    private static Scanner scanner = new Scanner(System.in);

    /**
     * MAIN METHOD - Entry point of the program
     * 
     * CONCEPT DEMONSTRATED: CONTROL STRUCTURES
     * - Uses while loop for menu repetition
     * - Uses switch-case for menu selection
     * - Uses if-else for conditional logic
     * 
     * @param args Command line arguments (not used in this program)
     */
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  Welcome to College Management System!");
        System.out.println("========================================");

        // Ensure output is flushed immediately
        System.out.flush();

        // Load data from files when program starts
        // CONCEPT DEMONSTRATED: FILE HANDLING
        FileHandler.loadData(students, teachers, classrooms);

        // Main menu loop - runs until user chooses to exit
        // CONCEPT DEMONSTRATED: LOOPS (while loop)
        while (true) {
            // Display main menu
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Teachers");
            System.out.println("3. Manage Classes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            System.out.flush(); // Ensure prompt is displayed before reading input

            // Read user's choice
            int choice = readIntInput();

            // CONCEPT DEMONSTRATED: CONTROL STRUCTURES (switch-case)
            // Switch statement handles different menu choices
            switch (choice) {
                case 1:
                    manageStudents(); // Go to student management menu
                    break;
                case 2:
                    manageTeachers(); // Go to teacher management menu
                    break;
                case 3:
                    manageClasses(); // Go to class management menu
                    break;
                case 4:
                    // Save data to files before exiting
                    FileHandler.saveData(students, teachers, classrooms);
                    System.out.println("\nData saved. Thank you for using College Management System!");
                    return; // Exit the program
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // ==================== STUDENT MANAGEMENT ====================

    /**
     * Student Management Menu
     * 
     * CONCEPT DEMONSTRATED: CRUD OPERATIONS
     * - Create: Add new student
     * - Read: View student details, List all students
     * - Update: Update student information
     * - Delete: Delete student record
     */
    private static void manageStudents() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add Student");
        System.out.println("2. View Student Details");
        System.out.println("3. Update Student Information");
        System.out.println("4. Delete Student");
        System.out.println("5. List All Students");
        System.out.print("Enter choice: ");

        int choice = readIntInput();

        switch (choice) {
            case 1:
                addStudent(); // CREATE operation
                break;
            case 2:
                viewStudent(); // READ operation
                break;
            case 3:
                updateStudent(); // UPDATE operation
                break;
            case 4:
                deleteStudent(); // DELETE operation
                break;
            case 5:
                listStudents(); // READ operation (list all)
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * CREATE OPERATION: Add a new student
     * 
     * CONCEPT DEMONSTRATED: OBJECT CREATION
     * - Creates new Student object using constructor
     * - Adds object to ArrayList collection
     * 
     * CONCEPT DEMONSTRATED: INPUT VALIDATION
     * - Validates user input before creating object
     * - Prevents duplicate IDs
     */
    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = readIntInput();

        // Check if ID already exists (prevent duplicates)
        if (findStudentById(id) != null) {
            System.out.println("Error: Student with this ID already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        // Validate name using InputValidator
        if (!InputValidator.validateName(name)) {
            System.out.println("Error: Invalid name. Name cannot be empty.");
            return;
        }

        System.out.print("Enter Age: ");
        int age = readIntInput();
        // Validate age using InputValidator
        if (!InputValidator.validateAge(age)) {
            System.out.println("Error: Invalid age. Age must be between 16 and 100.");
            return;
        }

        System.out.print("Enter Course/Class: ");
        String course = scanner.nextLine().trim();
        // Validate course using InputValidator
        if (!InputValidator.validateName(course)) {
            System.out.println("Error: Invalid course. Course cannot be empty.");
            return;
        }

        // Create new Student object and add to list
        // CONCEPT: OBJECT CREATION - new keyword creates an object
        students.add(new Student(id, name, age, course));
        System.out.println("Student added successfully!");
    }

    /**
     * READ OPERATION: View details of a specific student
     */
    private static void viewStudent() {
        System.out.print("Enter Student ID to view: ");
        int id = readIntInput();
        Student s = findStudentById(id);
        if (s != null) {
            System.out.println("\n--- Student Details ---");
            // toString() method is called automatically
            System.out.println(s);
        } else {
            System.out.println("Student not found.");
        }
    }

    /**
     * UPDATE OPERATION: Update student information
     * 
     * CONCEPT DEMONSTRATED: SETTER METHODS
     * - Uses setter methods to modify object attributes
     */
    private static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int editId = readIntInput();
        Student s = findStudentById(editId);
        if (s != null) {
            System.out.println("\nCurrent Details: " + s);
            System.out.print("Enter New Name (press Enter to keep current): ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty() && InputValidator.validateName(newName)) {
                s.setName(newName); // Use setter method
            } else if (!newName.isEmpty()) {
                System.out.println("Invalid name. Keeping current name.");
            }

            System.out.print("Enter New Age (press Enter to keep current): ");
            String ageInput = scanner.nextLine().trim();
            if (!ageInput.isEmpty()) {
                try {
                    int newAge = Integer.parseInt(ageInput);
                    if (InputValidator.validateAge(newAge)) {
                        s.setAge(newAge); // Use setter method
                    } else {
                        System.out.println("Invalid age. Keeping current age.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid age format. Keeping current age.");
                }
            }

            System.out.print("Enter New Course (press Enter to keep current): ");
            String newCourse = scanner.nextLine().trim();
            if (!newCourse.isEmpty() && InputValidator.validateName(newCourse)) {
                s.setCourse(newCourse); // Use setter method
            } else if (!newCourse.isEmpty()) {
                System.out.println("Invalid course. Keeping current course.");
            }

            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    /**
     * DELETE OPERATION: Delete a student record
     * 
     * CONCEPT DEMONSTRATED: COLLECTIONS
     * - Uses removeIf() method to remove object from ArrayList
     */
    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int delId = readIntInput();
        // removeIf returns true if something was removed
        boolean removed = students.removeIf(student -> student.getId() == delId);
        if (removed) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    /**
     * READ OPERATION: List all students
     * 
     * CONCEPT DEMONSTRATED: ENHANCED FOR LOOP
     * - for-each loop iterates through ArrayList
     */
    private static void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("\n--- List of Students ---");
            // Enhanced for loop - iterates through each student
            for (Student student : students) {
                System.out.println(student); // toString() called automatically
            }
        }
    }

    // ==================== TEACHER MANAGEMENT ====================

    /**
     * Teacher Management Menu
     * 
     * CONCEPT DEMONSTRATED: CRUD OPERATIONS
     * Same operations as Student Management
     */
    private static void manageTeachers() {
        System.out.println("\n--- Teacher Management ---");
        System.out.println("1. Add Teacher");
        System.out.println("2. View Teacher Details");
        System.out.println("3. Update Teacher Information");
        System.out.println("4. Delete Teacher");
        System.out.println("5. List All Teachers");
        System.out.print("Enter choice: ");

        int choice = readIntInput();

        switch (choice) {
            case 1:
                addTeacher();
                break;
            case 2:
                viewTeacher();
                break;
            case 3:
                updateTeacher();
                break;
            case 4:
                deleteTeacher();
                break;
            case 5:
                listTeachers();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * CREATE OPERATION: Add a new teacher
     */
    private static void addTeacher() {
        System.out.print("Enter Teacher ID: ");
        int id = readIntInput();

        if (findTeacherById(id) != null) {
            System.out.println("Error: Teacher with this ID already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        if (!InputValidator.validateName(name)) {
            System.out.println("Error: Invalid name. Name cannot be empty.");
            return;
        }

        System.out.print("Enter Subject: ");
        String subject = scanner.nextLine().trim();
        if (!InputValidator.validateName(subject)) {
            System.out.println("Error: Invalid subject. Subject cannot be empty.");
            return;
        }

        teachers.add(new Teacher(id, name, subject));
        System.out.println("Teacher added successfully!");
    }

    /**
     * READ OPERATION: View details of a specific teacher
     */
    private static void viewTeacher() {
        System.out.print("Enter Teacher ID to view: ");
        int id = readIntInput();
        Teacher t = findTeacherById(id);
        if (t != null) {
            System.out.println("\n--- Teacher Details ---");
            System.out.println(t);
        } else {
            System.out.println("Teacher not found.");
        }
    }

    /**
     * UPDATE OPERATION: Update teacher information
     */
    private static void updateTeacher() {
        System.out.print("Enter Teacher ID to update: ");
        int editId = readIntInput();
        Teacher t = findTeacherById(editId);
        if (t != null) {
            System.out.println("\nCurrent Details: " + t);
            System.out.print("Enter New Name (press Enter to keep current): ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty() && InputValidator.validateName(newName)) {
                t.setName(newName);
            } else if (!newName.isEmpty()) {
                System.out.println("Invalid name. Keeping current name.");
            }

            System.out.print("Enter New Subject (press Enter to keep current): ");
            String newSubject = scanner.nextLine().trim();
            if (!newSubject.isEmpty() && InputValidator.validateName(newSubject)) {
                t.setSubject(newSubject);
            } else if (!newSubject.isEmpty()) {
                System.out.println("Invalid subject. Keeping current subject.");
            }

            System.out.println("Teacher updated successfully!");
        } else {
            System.out.println("Teacher not found.");
        }
    }

    /**
     * DELETE OPERATION: Delete a teacher record
     */
    private static void deleteTeacher() {
        System.out.print("Enter Teacher ID to delete: ");
        int delId = readIntInput();
        boolean removed = teachers.removeIf(teacher -> teacher.getId() == delId);
        if (removed) {
            System.out.println("Teacher deleted successfully.");
        } else {
            System.out.println("Teacher not found.");
        }
    }

    /**
     * READ OPERATION: List all teachers
     */
    private static void listTeachers() {
        if (teachers.isEmpty()) {
            System.out.println("No teachers found.");
        } else {
            System.out.println("\n--- List of Teachers ---");
            for (Teacher teacher : teachers) {
                System.out.println(teacher);
            }
        }
    }

    // ==================== CLASS MANAGEMENT ====================

    /**
     * Class Management Menu
     * 
     * CONCEPT DEMONSTRATED: CRUD OPERATIONS
     * Includes additional operations like assigning teacher and adding students
     */
    private static void manageClasses() {
        System.out.println("\n--- Class Management ---");
        System.out.println("1. Add Class");
        System.out.println("2. View Class Details");
        System.out.println("3. Update Class Information");
        System.out.println("4. Delete Class");
        System.out.println("5. Assign Teacher to Class");
        System.out.println("6. Add Student to Class");
        System.out.println("7. Remove Student from Class");
        System.out.println("8. List All Classes");
        System.out.print("Enter choice: ");

        int choice = readIntInput();

        switch (choice) {
            case 1:
                addClass();
                break;
            case 2:
                viewClass();
                break;
            case 3:
                updateClass();
                break;
            case 4:
                deleteClass();
                break;
            case 5:
                assignTeacherToClass();
                break;
            case 6:
                addStudentToClass();
                break;
            case 7:
                removeStudentFromClass();
                break;
            case 8:
                listClasses();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * CREATE OPERATION: Add a new class
     */
    private static void addClass() {
        System.out.print("Enter Class ID: ");
        int id = readIntInput();

        if (findClassroomById(id) != null) {
            System.out.println("Error: Class with this ID already exists!");
            return;
        }

        System.out.print("Enter Class Name (e.g. Math 101): ");
        String name = scanner.nextLine().trim();
        if (!InputValidator.validateName(name)) {
            System.out.println("Error: Invalid class name. Class name cannot be empty.");
            return;
        }

        classrooms.add(new Classroom(id, name));
        System.out.println("Class created successfully!");
    }

    /**
     * READ OPERATION: View details of a specific class
     */
    private static void viewClass() {
        System.out.print("Enter Class ID to view: ");
        int id = readIntInput();
        Classroom c = findClassroomById(id);
        if (c != null) {
            System.out.println("\n--- Class Details ---");
            System.out.println(c);
            if (c.getTeacher() != null) {
                System.out.println("Assigned Teacher: " + c.getTeacher());
            }
            if (!c.getStudents().isEmpty()) {
                System.out.println("Students in this class:");
                for (Student s : c.getStudents()) {
                    System.out.println("  - " + s);
                }
            }
        } else {
            System.out.println("Class not found.");
        }
    }

    /**
     * UPDATE OPERATION: Update class information
     */
    private static void updateClass() {
        System.out.print("Enter Class ID to update: ");
        int editId = readIntInput();
        Classroom c = findClassroomById(editId);
        if (c != null) {
            System.out.println("\nCurrent Details: " + c);
            System.out.print("Enter New Class Name (press Enter to keep current): ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty() && InputValidator.validateName(newName)) {
                c.setClassName(newName);
                System.out.println("Class name updated successfully!");
            } else if (!newName.isEmpty()) {
                System.out.println("Invalid class name. Keeping current name.");
            } else {
                System.out.println("No changes made.");
            }
        } else {
            System.out.println("Class not found.");
        }
    }

    /**
     * DELETE OPERATION: Delete a class record
     */
    private static void deleteClass() {
        System.out.print("Enter Class ID to delete: ");
        int delId = readIntInput();
        boolean removed = classrooms.removeIf(cl -> cl.getId() == delId);
        if (removed) {
            System.out.println("Class deleted successfully.");
        } else {
            System.out.println("Class not found.");
        }
    }

    /**
     * Assign a teacher to a class
     */
    private static void assignTeacherToClass() {
        System.out.print("Enter Class ID: ");
        int classId = readIntInput();
        Classroom classroom = findClassroomById(classId);
        if (classroom != null) {
            System.out.print("Enter Teacher ID to assign: ");
            int teacherId = readIntInput();
            Teacher teacher = findTeacherById(teacherId);
            if (teacher != null) {
                classroom.setTeacher(teacher);
                System.out.println("Teacher assigned to class successfully!");
            } else {
                System.out.println("Teacher not found.");
            }
        } else {
            System.out.println("Class not found.");
        }
    }

    /**
     * Add a student to a class
     */
    private static void addStudentToClass() {
        System.out.print("Enter Class ID: ");
        int cId = readIntInput();
        Classroom cl = findClassroomById(cId);
        if (cl != null) {
            System.out.print("Enter Student ID to add: ");
            int sId = readIntInput();
            Student s = findStudentById(sId);
            if (s != null) {
                // Check if student is already in the class
                boolean alreadyInClass = false;
                for (Student student : cl.getStudents()) {
                    if (student.getId() == sId) {
                        alreadyInClass = true;
                        break;
                    }
                }
                if (alreadyInClass) {
                    System.out.println("Student is already in this class.");
                } else {
                    cl.addStudent(s);
                    System.out.println("Student added to class successfully!");
                }
            } else {
                System.out.println("Student not found.");
            }
        } else {
            System.out.println("Class not found.");
        }
    }

    /**
     * Remove a student from a class
     */
    private static void removeStudentFromClass() {
        System.out.print("Enter Class ID: ");
        int cId = readIntInput();
        Classroom cl = findClassroomById(cId);
        if (cl != null) {
            System.out.print("Enter Student ID to remove: ");
            int sId = readIntInput();
            cl.removeStudent(sId);
            System.out.println("Student removed from class successfully!");
        } else {
            System.out.println("Class not found.");
        }
    }

    /**
     * READ OPERATION: List all classes
     */
    private static void listClasses() {
        if (classrooms.isEmpty()) {
            System.out.println("No classes found.");
        } else {
            System.out.println("\n--- List of Classes ---");
            for (Classroom room : classrooms) {
                System.out.println(room);
            }
        }
    }

    // ==================== HELPER METHODS ====================

    /**
     * Helper method to safely read integer input
     * 
     * CONCEPT DEMONSTRATED: EXCEPTION HANDLING
     * - Uses try-catch to handle NumberFormatException
     * - Prevents program from crashing if user enters non-numeric input
     * - Keeps asking until valid input is provided
     * 
     * @return Valid integer entered by user
     */
    private static int readIntInput() {
        while (true) {
            try {
                // Flush output to ensure prompt is displayed
                System.out.flush();
                String input = scanner.nextLine();
                if (input == null || input.trim().isEmpty()) {
                    System.out.print("Invalid input. Please enter a number: ");
                    continue;
                }
                return Integer.parseInt(input.trim()); // Convert string to integer
            } catch (NumberFormatException e) {
                // Handle error if input is not a number
                System.out.print("Invalid input. Please enter a number: ");
            } catch (Exception e) {
                // Handle any other input errors
                System.out.print("Error reading input. Please try again: ");
            }
        }
    }

    /**
     * Helper method to find a student by ID
     * 
     * CONCEPT DEMONSTRATED: LINEAR SEARCH
     * - Searches through ArrayList to find matching ID
     * 
     * @param id The student ID to search for
     * @return Student object if found, null otherwise
     */
    private static Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }

    /**
     * Helper method to find a teacher by ID
     * 
     * @param id The teacher ID to search for
     * @return Teacher object if found, null otherwise
     */
    private static Teacher findTeacherById(int id) {
        for (Teacher t : teachers) {
            if (t.getId() == id)
                return t;
        }
        return null;
    }

    /**
     * Helper method to find a classroom by ID
     * 
     * @param id The classroom ID to search for
     * @return Classroom object if found, null otherwise
     */
    private static Classroom findClassroomById(int id) {
        for (Classroom c : classrooms) {
            if (c.getId() == id)
                return c;
        }
        return null;
    }
}
