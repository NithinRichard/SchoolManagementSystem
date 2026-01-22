package com.college.model;

/**
 * CLASS: Student
 * 
 * CONCEPT DEMONSTRATED: INHERITANCE
 * - Student extends Person (Student IS-A Person)
 * - Student inherits id and name from Person class
 * - Student can use all public/protected methods from Person
 * 
 * CONCEPT DEMONSTRATED: METHOD OVERRIDING
 * - Student overrides the abstract getDetails() method from Person
 * - Student also overrides toString() method from Object class
 */
public class Student extends Person {

    // Additional fields specific to Student
    // CONCEPT: ENCAPSULATION - private fields cannot be accessed directly
    private int age; // Age of the student
    private String course; // Course or Class name (REQUIREMENT: Each student must have a course/class)

    /**
     * CONSTRUCTOR
     * 
     * CONCEPT DEMONSTRATED: CONSTRUCTOR CHAINING
     * - super(id, name) calls the parent class (Person) constructor
     * - This initializes the inherited fields (id and name)
     * - Then we initialize Student-specific fields (age and course)
     * 
     * @param id     Student ID (inherited from Person)
     * @param name   Student name (inherited from Person)
     * @param age    Student age
     * @param course Course or Class name
     */
    public Student(int id, String name, int age, String course) {
        super(id, name); // Call parent class constructor
        this.age = age;
        this.course = course;
    }

    // GETTERS AND SETTERS
    // CONCEPT: ENCAPSULATION - controlled access to private fields

    /**
     * Getter for age
     * 
     * @return The student's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter for age
     * 
     * @param age The new age value
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Getter for course
     * 
     * @return The student's course/class
     */
    public String getCourse() {
        return course;
    }

    /**
     * Setter for course
     * 
     * @param course The new course value
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * METHOD OVERRIDING
     * 
     * CONCEPT DEMONSTRATED: METHOD OVERRIDING
     * - This method overrides the abstract getDetails() method from Person class
     * - @Override annotation ensures we're actually overriding a parent method
     * - Each subclass (Student, Teacher) provides its own implementation
     * 
     * CONCEPT DEMONSTRATED: POLYMORPHISM
     * - When we call getDetails() on a Student object, this version runs
     * - When we call getDetails() on a Teacher object, Teacher's version runs
     * 
     * @return String containing student details
     */
    @Override
    public String getDetails() {
        return "Student [ID=" + id + ", Name=" + name + ", Age=" + age + ", Course=" + course + "]";
    }

    /**
     * METHOD OVERRIDING
     * 
     * CONCEPT DEMONSTRATED: METHOD OVERRIDING
     * - Overrides toString() method from Object class (parent of all classes)
     * - This is called automatically when we print a Student object
     * - Example: System.out.println(student) will call this method
     * 
     * @return String representation of the student
     */
    @Override
    public String toString() {
        return getDetails(); // Reuse getDetails() method
    }
}
