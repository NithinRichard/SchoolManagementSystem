package com.college.model;

/**
 * CLASS: Teacher
 * 
 * CONCEPT DEMONSTRATED: INHERITANCE
 * - Teacher extends Person (Teacher IS-A Person)
 * - Teacher inherits id and name from Person class
 * - Both Student and Teacher share common Person attributes
 * 
 * CONCEPT DEMONSTRATED: METHOD OVERRIDING
 * - Teacher overrides the abstract getDetails() method from Person
 * - Teacher also overrides toString() method from Object class
 */
public class Teacher extends Person {

    // Additional field specific to Teacher
    // CONCEPT: ENCAPSULATION - private field
    private String subject; // The subject the teacher teaches

    /**
     * CONSTRUCTOR
     * 
     * CONCEPT DEMONSTRATED: CONSTRUCTOR CHAINING
     * - super(id, name) calls the parent class (Person) constructor
     * - This initializes the inherited fields (id and name)
     * - Then we initialize Teacher-specific field (subject)
     * 
     * @param id      Teacher ID (inherited from Person)
     * @param name    Teacher name (inherited from Person)
     * @param subject Subject taught by the teacher
     */
    public Teacher(int id, String name, String subject) {
        super(id, name); // Call parent class constructor
        this.subject = subject;
    }

    // GETTERS AND SETTERS
    // CONCEPT: ENCAPSULATION - controlled access to private fields

    /**
     * Getter for subject
     * 
     * @return The subject taught by the teacher
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Setter for subject
     * 
     * @param subject The new subject value
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * METHOD OVERRIDING
     * 
     * CONCEPT DEMONSTRATED: METHOD OVERRIDING
     * - This method overrides the abstract getDetails() method from Person class
     * - Teacher provides its own implementation different from Student
     * 
     * CONCEPT DEMONSTRATED: POLYMORPHISM
     * - Same method name (getDetails) but different behavior for Student vs Teacher
     * - This is called runtime polymorphism
     * 
     * @return String containing teacher details
     */
    @Override
    public String getDetails() {
        return "Teacher [ID=" + id + ", Name=" + name + ", Subject=" + subject + "]";
    }

    /**
     * METHOD OVERRIDING
     * 
     * CONCEPT DEMONSTRATED: METHOD OVERRIDING
     * - Overrides toString() method from Object class
     * - Called automatically when we print a Teacher object
     * 
     * @return String representation of the teacher
     */
    @Override
    public String toString() {
        return getDetails(); // Reuse getDetails() method
    }
}
