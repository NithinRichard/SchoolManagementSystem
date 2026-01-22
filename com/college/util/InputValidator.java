package com.college.util;

/**
 * UTILITY CLASS: InputValidator
 * 
 * CONCEPT DEMONSTRATED: STATIC METHODS
 * - All methods in this class are static
 * - Static methods can be called without creating an object
 * - Example: InputValidator.validateName("John") instead of new
 * InputValidator().validateName("John")
 * 
 * PURPOSE: Input Validation (BONUS FEATURE)
 * - Validates user inputs to prevent invalid data
 * - Makes the program more robust and user-friendly
 * - Prevents crashes from invalid input
 */
public class InputValidator {

    /**
     * Validates a name (checks if it's not empty)
     * 
     * CONCEPT DEMONSTRATED: STATIC METHODS
     * - Can be called directly using class name: InputValidator.validateName(name)
     * 
     * @param name The name to validate
     * @return true if name is valid (not null and not empty), false otherwise
     */
    public static boolean validateName(String name) {
        // Check if name is not null and not empty after removing spaces
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Validates an age (checks if it's within reasonable range)
     * 
     * CONCEPT DEMONSTRATED: STATIC METHODS
     * 
     * @param age The age to validate
     * @return true if age is between 16 and 100, false otherwise
     */
    public static boolean validateAge(int age) {
        // Age should be reasonable for college students (16-100)
        return age >= 16 && age <= 100;
    }

    /**
     * Validates an ID (checks if it's positive)
     * 
     * CONCEPT DEMONSTRATED: STATIC METHODS
     * 
     * @param id The ID to validate
     * @return true if ID is positive, false otherwise
     */
    public static boolean validateId(int id) {
        // ID should be a positive number
        return id > 0;
    }
}
