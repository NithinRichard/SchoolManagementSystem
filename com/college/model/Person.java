package com.college.model;

/**
 * ABSTRACT CLASS: Person
 * 
 * This is an abstract base class that represents a Person in the college.
 * 
 * CONCEPT DEMONSTRATED: INHERITANCE
 * - Both Student and Teacher classes will inherit from this Person class
 * - This allows us to share common attributes (id, name) between Student and
 * Teacher
 * - We use "abstract" keyword because we don't want to create Person objects
 * directly
 * 
 * CONCEPT DEMONSTRATED: ABSTRACT CLASSES
 * - Abstract classes cannot be instantiated (you can't do: new Person())
 * - They can have abstract methods (methods without implementation)
 * - Subclasses must implement all abstract methods
 */
public abstract class Person {

    // Protected fields - can be accessed by subclasses (Student and Teacher)
    // CONCEPT: ENCAPSULATION - using protected access modifier
    protected int id; // Unique identifier for the person
    protected String name; // Name of the person

    /**
     * CONSTRUCTOR
     * This constructor initializes the Person object with id and name
     * 
     * CONCEPT DEMONSTRATED: CONSTRUCTORS
     * - Constructors are called when we create an object using 'new'
     * - Subclasses will call this constructor using 'super()'
     * 
     * @param id   The unique identifier
     * @param name The person's name
     */
    public Person(int id, String name) {
        this.id = id; // 'this' refers to the current object
        this.name = name;
    }

    // GETTERS AND SETTERS
    // CONCEPT DEMONSTRATED: ENCAPSULATION
    // - We provide controlled access to private/protected fields through methods

    /**
     * Getter method to retrieve the ID
     * 
     * @return The person's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method to update the ID
     * 
     * @param id The new ID value
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter method to retrieve the name
     * 
     * @return The person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method to update the name
     * 
     * @param name The new name value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ABSTRACT METHOD
     * 
     * CONCEPT DEMONSTRATED: ABSTRACT METHODS
     * - This method has no body (no implementation)
     * - Any class that extends Person MUST implement this method
     * - This ensures all Person subclasses have a way to get their details
     * 
     * CONCEPT DEMONSTRATED: POLYMORPHISM
     * - Different subclasses will implement this differently
     * - Student will return student details, Teacher will return teacher details
     * 
     * @return A string containing the person's details
     */
    public abstract String getDetails();
}
