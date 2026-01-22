package com.college.model;

import com.college.interfaces.Manageable;
import java.util.ArrayList; // Import ArrayList collection

/**
 * CLASS: Classroom
 * 
 * CONCEPT DEMONSTRATED: INTERFACE IMPLEMENTATION
 * - Classroom implements Manageable interface
 * - This means Classroom must provide implementations for all methods in
 * Manageable
 * 
 * CONCEPT DEMONSTRATED: COLLECTIONS
 * - Uses ArrayList to store multiple Student objects
 * - ArrayList is a dynamic array that can grow/shrink as needed
 */
public class Classroom implements Manageable {

    // Fields
    // CONCEPT: ENCAPSULATION - private fields
    private int id; // Unique identifier for the classroom
    private String className; // Name of the class (e.g., "Math 101")
    private Teacher teacher; // The teacher assigned to this class
    private ArrayList<Student> students; // List of students in this class

    /**
     * CONSTRUCTOR
     * 
     * CONCEPT DEMONSTRATED: CONSTRUCTORS
     * - Initializes all fields when creating a new Classroom object
     * - students ArrayList is initialized as empty
     * 
     * @param id        Classroom ID
     * @param className Name of the class
     */
    public Classroom(int id, String className) {
        this.id = id;
        this.className = className;
        this.students = new ArrayList<>(); // Initialize empty list
        this.teacher = null; // No teacher assigned initially
    }

    // METHODS TO MANAGE THE CLASSROOM

    /**
     * Assigns a teacher to this classroom
     * 
     * @param teacher The teacher to assign
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Adds a student to this classroom
     * 
     * CONCEPT DEMONSTRATED: COLLECTIONS
     * - Uses ArrayList's add() method to add a student to the list
     * 
     * @param student The student to add
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Removes a student from this classroom
     * 
     * CONCEPT DEMONSTRATED: COLLECTIONS & LAMBDA EXPRESSIONS
     * - Uses removeIf() method with a lambda expression
     * - Lambda: s -> s.getId() == studentId (checks if student ID matches)
     * 
     * @param studentId The ID of the student to remove
     */
    public void removeStudent(int studentId) {
        students.removeIf(s -> s.getId() == studentId);
    }

    // GETTERS AND SETTERS
    // CONCEPT: ENCAPSULATION

    /**
     * Getter for ID
     * 
     * @return The classroom ID
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for class name
     * 
     * @return The class name
     */
    public String getClassName() {
        return className;
    }

    /**
     * Setter for class name
     * 
     * @param className The new class name
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Getter for teacher
     * 
     * @return The assigned teacher (or null if none)
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Getter for students list
     * 
     * @return The list of students in this classroom
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * INTERFACE METHOD IMPLEMENTATION
     * 
     * CONCEPT DEMONSTRATED: INTERFACE IMPLEMENTATION
     * - This method is required because Classroom implements Manageable interface
     * - Must provide the body for this method
     * 
     * @return String containing classroom information
     */
    @Override
    public String getInfo() {
        String teacherName = (teacher != null) ? teacher.getName() : "None";
        return "Classroom [ID=" + id + ", Name=" + className + ", Teacher=" + teacherName
                + ", Students Count=" + students.size() + "]";
    }

    /**
     * METHOD OVERRIDING
     * 
     * CONCEPT DEMONSTRATED: METHOD OVERRIDING
     * - Overrides toString() from Object class
     * - Uses getInfo() method to get the string representation
     * 
     * @return String representation of the classroom
     */
    @Override
    public String toString() {
        return getInfo();
    }
}
