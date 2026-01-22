package com.college.interfaces;

/**
 * INTERFACE: Manageable
 * 
 * CONCEPT DEMONSTRATED: INTERFACES
 * - An interface defines a contract that classes must follow
 * - Any class that implements this interface MUST provide implementations for
 * all its methods
 * - Interfaces help achieve multiple inheritance (a class can implement
 * multiple interfaces)
 * - In this project, Classroom implements Manageable interface
 * 
 * WHY USE INTERFACES?
 * - They ensure consistency - all manageable entities have the same methods
 * - They allow polymorphism - we can treat different classes the same way
 * - They make code more flexible and maintainable
 */
public interface Manageable {

    /**
     * Method to get information about the manageable entity
     * 
     * CONCEPT DEMONSTRATED: INTERFACE METHODS
     * - Interface methods are implicitly public and abstract
     * - The implementing class must provide the body for this method
     * 
     * @return String representation of the entity's information
     */
    String getInfo();

    /**
     * Method to get the ID of the manageable entity
     * 
     * @return The ID of the entity
     */
    int getId();
}
