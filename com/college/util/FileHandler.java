package com.college.util;

import com.college.model.*;
import java.io.*; // Import for file operations
import java.util.ArrayList;

/**
 * UTILITY CLASS: FileHandler
 * 
 * CONCEPT DEMONSTRATED: FILE HANDLING (BONUS FEATURE)
 * - Reads data from files when program starts
 * - Saves data to files when program exits
 * - This allows data persistence (data survives after program closes)
 * 
 * CONCEPT DEMONSTRATED: EXCEPTION HANDLING
 * - Uses try-catch blocks to handle file operation errors
 * - Prevents program from crashing if files don't exist or can't be read
 * 
 * CONCEPT DEMONSTRATED: STATIC METHODS
 * - All methods are static, can be called without creating objects
 */
public class FileHandler {

    // File names where data will be stored
    private static final String STUDENTS_FILE = "students.txt";
    private static final String TEACHERS_FILE = "teachers.txt";
    private static final String CLASSROOMS_FILE = "classrooms.txt";

    /**
     * Saves all data to files
     * 
     * CONCEPT DEMONSTRATED: FILE HANDLING
     * - Writes student, teacher, and classroom data to text files
     * - Uses PrintWriter to write data
     * - Data is saved in CSV format (comma-separated values)
     * 
     * CONCEPT DEMONSTRATED: EXCEPTION HANDLING
     * - try-catch block handles IOException if file writing fails
     * 
     * @param students   List of students to save
     * @param teachers   List of teachers to save
     * @param classrooms List of classrooms to save
     */
    public static void saveData(ArrayList<Student> students,
            ArrayList<Teacher> teachers,
            ArrayList<Classroom> classrooms) {
        try {
            // Save each list to its respective file
            saveStudents(students);
            saveTeachers(teachers);
            saveClassrooms(classrooms, students, teachers);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            // Handle error if file writing fails
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Loads all data from files
     * 
     * CONCEPT DEMONSTRATED: FILE HANDLING
     * - Reads student, teacher, and classroom data from text files
     * - Uses BufferedReader to read data line by line
     * 
     * CONCEPT DEMONSTRATED: EXCEPTION HANDLING
     * - try-catch handles IOException if files don't exist (first run)
     * - This is normal - program will start with empty lists on first run
     * 
     * @param students   List to populate with students
     * @param teachers   List to populate with teachers
     * @param classrooms List to populate with classrooms
     */
    public static void loadData(ArrayList<Student> students,
            ArrayList<Teacher> teachers,
            ArrayList<Classroom> classrooms) {
        try {
            // Load each list from its respective file
            loadStudents(students);
            loadTeachers(teachers);
            loadClassrooms(classrooms, students, teachers);
            System.out.println("Data loaded successfully!");
        } catch (IOException e) {
            // Files don't exist yet - this is normal on first run
            System.out.println("No previous data found. Starting with empty lists.");
        } catch (Exception e) {
            // Handle any other errors
            System.err.println("Error loading data: " + e.getMessage());
        }
    }

    /**
     * Saves students to file
     * Format: id,name,age,course
     */
    private static void saveStudents(ArrayList<Student> students) throws IOException {
        // try-with-resources: automatically closes file when done
        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENTS_FILE))) {
            for (Student s : students) {
                // Write each student as a comma-separated line
                writer.println(s.getId() + "," + s.getName() + "," + s.getAge() + "," + s.getCourse());
            }
        }
    }

    /**
     * Saves teachers to file
     * Format: id,name,subject
     */
    private static void saveTeachers(ArrayList<Teacher> teachers) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEACHERS_FILE))) {
            for (Teacher t : teachers) {
                // Write each teacher as a comma-separated line
                writer.println(t.getId() + "," + t.getName() + "," + t.getSubject());
            }
        }
    }

    /**
     * Saves classrooms to file
     * Format: id,className,teacherId,studentCount,studentId1,studentId2,...
     */
    private static void saveClassrooms(ArrayList<Classroom> classrooms,
            ArrayList<Student> students,
            ArrayList<Teacher> teachers) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CLASSROOMS_FILE))) {
            for (Classroom c : classrooms) {
                // Write classroom ID and name
                writer.print(c.getId() + "," + c.getClassName());

                // Write teacher ID (or "null" if no teacher)
                if (c.getTeacher() != null) {
                    writer.print("," + c.getTeacher().getId());
                } else {
                    writer.print(",null");
                }

                // Write number of students and their IDs
                writer.print("," + c.getStudents().size());
                for (Student s : c.getStudents()) {
                    writer.print("," + s.getId());
                }
                writer.println(); // Move to next line
            }
        }
    }

    /**
     * Loads students from file
     */
    private static void loadStudents(ArrayList<Student> students) throws IOException {
        File file = new File(STUDENTS_FILE);
        if (!file.exists()) {
            return; // File doesn't exist yet, nothing to load
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Read each line from file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Split by comma
                if (parts.length == 4) {
                    // Parse the data and create Student object
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String course = parts[3];
                    students.add(new Student(id, name, age, course));
                }
            }
        }
    }

    /**
     * Loads teachers from file
     */
    private static void loadTeachers(ArrayList<Teacher> teachers) throws IOException {
        File file = new File(TEACHERS_FILE);
        if (!file.exists()) {
            return; // File doesn't exist yet
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    // Parse the data and create Teacher object
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String subject = parts[2];
                    teachers.add(new Teacher(id, name, subject));
                }
            }
        }
    }

    /**
     * Loads classrooms from file
     */
    private static void loadClassrooms(ArrayList<Classroom> classrooms,
            ArrayList<Student> students,
            ArrayList<Teacher> teachers) throws IOException {
        File file = new File(CLASSROOMS_FILE);
        if (!file.exists()) {
            return; // File doesn't exist yet
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    // Parse classroom data
                    int id = Integer.parseInt(parts[0]);
                    String className = parts[1];
                    Classroom classroom = new Classroom(id, className);

                    // Load teacher if exists
                    if (!parts[2].equals("null")) {
                        int teacherId = Integer.parseInt(parts[2]);
                        Teacher teacher = findTeacherById(teachers, teacherId);
                        if (teacher != null) {
                            classroom.setTeacher(teacher);
                        }
                    }

                    // Load students
                    if (parts.length > 4) {
                        int studentCount = Integer.parseInt(parts[3]);
                        for (int i = 4; i < parts.length && i < 4 + studentCount; i++) {
                            int studentId = Integer.parseInt(parts[i]);
                            Student student = findStudentById(students, studentId);
                            if (student != null) {
                                classroom.addStudent(student);
                            }
                        }
                    }

                    classrooms.add(classroom);
                }
            }
        }
    }

    /**
     * Helper method to find a student by ID
     */
    private static Student findStudentById(ArrayList<Student> students, int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    /**
     * Helper method to find a teacher by ID
     */
    private static Teacher findTeacherById(ArrayList<Teacher> teachers, int id) {
        for (Teacher t : teachers) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}
