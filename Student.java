package SchoolManagementSystem;

/**
 * Represents a Student in the school.
 * This class is a "blueprint" for creating student objects.
 */
public class Student {
    // Fields (Attributes) - These store the data for each student
    private int id;         // Unique ID for the student
    private String name;    // Name of the student
    private int age;        // Age of the student

    /**
     * Constructor: This method is called when we create a new Student object.
     * It initializes the student's data.
     * 
     * @param id   The unique ID
     * @param name The student's name
     * @param age  The student's age
     */
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getters and Setters - Methods to access and modify the private fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * toString method: Converts the object into a readable String format.
     * This is useful when we want to print the student details.
     */
    @Override
    public String toString() {
        return "Student [ID=" + id + ", Name=" + name + ", Age=" + age + "]";
    }
}
