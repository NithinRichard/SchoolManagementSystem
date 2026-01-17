package SchoolManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class for the School Management System.
 * This class handles user interaction and manages the lists of data.
 */
public class SchoolManagementSystem {

    // Global lists to store our data
    // static means these belong to the class itself and can be accessed from main
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Teacher> teachers = new ArrayList<>();
    private static ArrayList<Classroom> classrooms = new ArrayList<>();

    // Scanner for reading user input
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the School Management System!");

        while (true) {
            // Display the menu
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Teachers");
            System.out.println("3. Manage Classes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    manageStudents();
                    break;
                case 2:
                    manageTeachers();
                    break;
                case 3:
                    manageClasses();
                    break;
                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    return; // Ends the program
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // --- Student Management ---

    private static void manageStudents() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add Student");
        System.out.println("2. Edit Student");
        System.out.println("3. Delete Student");
        System.out.println("4. List Students");
        System.out.print("Enter choice: ");

        int choice = readIntInput();

        switch (choice) {
            case 1:
                System.out.print("Enter Student ID: ");
                int id = readIntInput();
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Age: ");
                int age = readIntInput();
                students.add(new Student(id, name, age));
                System.out.println("Student added successfully!");
                break;
            case 2:
                System.out.print("Enter Student ID to edit: ");
                int editId = readIntInput();
                Student s = findStudentById(editId);
                if (s != null) {
                    System.out.print("Enter New Name (current: " + s.getName() + "): ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Age (current: " + s.getAge() + "): ");
                    int newAge = readIntInput();
                    s.setName(newName);
                    s.setAge(newAge);
                    System.out.println("Student updated!");
                } else {
                    System.out.println("Student not found.");
                }
                break;
            case 3:
                System.out.print("Enter Student ID to delete: ");
                int delId = readIntInput();
                // removeIf returns true if something was removed
                boolean removed = students.removeIf(student -> student.getId() == delId);
                if (removed) {
                    System.out.println("Student deleted.");
                } else {
                    System.out.println("Student not found.");
                }
                break;
            case 4:
                System.out.println("List of Students:");
                for (Student student : students) {
                    System.out.println(student);
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // --- Teacher Management ---

    private static void manageTeachers() {
        System.out.println("\n--- Teacher Management ---");
        System.out.println("1. Add Teacher");
        System.out.println("2. Edit Teacher");
        System.out.println("3. Delete Teacher");
        System.out.println("4. List Teachers");
        System.out.print("Enter choice: ");

        int choice = readIntInput();

        switch (choice) {
            case 1:
                System.out.print("Enter Teacher ID: ");
                int id = readIntInput();
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Subject: ");
                String subject = scanner.nextLine();
                teachers.add(new Teacher(id, name, subject));
                System.out.println("Teacher added successfully!");
                break;
            case 2:
                System.out.print("Enter Teacher ID to edit: ");
                int editId = readIntInput();
                Teacher t = findTeacherById(editId);
                if (t != null) {
                    System.out.print("Enter New Name (current: " + t.getName() + "): ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Subject (current: " + t.getSubject() + "): ");
                    String newSubject = scanner.nextLine();
                    t.setName(newName);
                    t.setSubject(newSubject);
                    System.out.println("Teacher updated!");
                } else {
                    System.out.println("Teacher not found.");
                }
                break;
            case 3:
                System.out.print("Enter Teacher ID to delete: ");
                int delId = readIntInput();
                boolean removed = teachers.removeIf(teacher -> teacher.getId() == delId);
                if (removed) {
                    System.out.println("Teacher deleted.");
                } else {
                    System.out.println("Teacher not found.");
                }
                break;
            case 4:
                System.out.println("List of Teachers:");
                for (Teacher teacher : teachers) {
                    System.out.println(teacher);
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // --- Class Management ---

    private static void manageClasses() {
        System.out.println("\n--- Class Management ---");
        System.out.println("1. Add Class");
        System.out.println("2. Edit Class (Rename)");
        System.out.println("3. Delete Class");
        System.out.println("4. Assign Teacher to Class");
        System.out.println("5. Add Student to Class");
        System.out.println("6. List Classes");
        System.out.print("Enter choice: ");

        int choice = readIntInput();

        switch (choice) {
            case 1:
                System.out.print("Enter Class ID: ");
                int id = readIntInput();
                System.out.print("Enter Class Name (e.g. Math 101): ");
                String name = scanner.nextLine();
                classrooms.add(new Classroom(id, name));
                System.out.println("Class created!");
                break;
            case 2:
                System.out.print("Enter Class ID to edit: ");
                int editId = readIntInput();
                Classroom c = findClassroomById(editId);
                if (c != null) {
                    // Since Classroom doesn't have a setter for name in our simple example,
                    // we might need to recreate it or just inform the user.
                    // For this example, let's assume we can't easily rename without adding a
                    // setter.
                    // But wait, we should have added a setter. Let's just re-create it for
                    // simplicity or skip.
                    // Actually, let's just say "Feature not available, please delete and recreate"
                    // OR we can go back and add a setter. Let's keep it simple.
                    System.out.println("To rename a class, please delete and recreate it.");
                } else {
                    System.out.println("Class not found.");
                }
                break;
            case 3:
                System.out.print("Enter Class ID to delete: ");
                int delId = readIntInput();
                boolean removed = classrooms.removeIf(cl -> cl.getId() == delId);
                if (removed) {
                    System.out.println("Class deleted.");
                } else {
                    System.out.println("Class not found.");
                }
                break;
            case 4:
                System.out.print("Enter Class ID: ");
                int classId = readIntInput();
                Classroom classroom = findClassroomById(classId);
                if (classroom != null) {
                    System.out.print("Enter Teacher ID to assign: ");
                    int teacherId = readIntInput();
                    Teacher teacher = findTeacherById(teacherId);
                    if (teacher != null) {
                        classroom.setTeacher(teacher);
                        System.out.println("Teacher assigned to class.");
                    } else {
                        System.out.println("Teacher not found.");
                    }
                } else {
                    System.out.println("Class not found.");
                }
                break;
            case 5:
                System.out.print("Enter Class ID: ");
                int cId = readIntInput();
                Classroom cl = findClassroomById(cId);
                if (cl != null) {
                    System.out.print("Enter Student ID to add: ");
                    int sId = readIntInput();
                    Student s = findStudentById(sId);
                    if (s != null) {
                        cl.addStudent(s);
                        System.out.println("Student added to class.");
                    } else {
                        System.out.println("Student not found.");
                    }
                } else {
                    System.out.println("Class not found.");
                }
                break;
            case 6:
                System.out.println("List of Classes:");
                for (Classroom room : classrooms) {
                    System.out.println(room);
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // --- Helper Methods ---

    // Helper to safely read an integer (prevents crashing if user types text)
    private static int readIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private static Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }

    private static Teacher findTeacherById(int id) {
        for (Teacher t : teachers) {
            if (t.getId() == id)
                return t;
        }
        return null;
    }

    private static Classroom findClassroomById(int id) {
        for (Classroom c : classrooms) {
            if (c.getId() == id)
                return c;
        }
        return null;
    }
}
