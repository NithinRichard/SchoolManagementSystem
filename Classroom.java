package SchoolManagementSystem;

import java.util.ArrayList; // Import ArrayList to store a list of students

/**
 * Represents a Classroom (e.g., "Grade 10 - Math").
 * A classroom has a name, one teacher, and a list of students.
 */
public class Classroom {
    private int id;
    private String className;
    private Teacher teacher; // The teacher assigned to this class
    private ArrayList<Student> students; // A list to hold multiple Student objects

    public Classroom(int id, String className) {
        this.id = id;
        this.className = className;
        this.students = new ArrayList<>(); // Initialize the list as empty
    }

    // Methods to manage the class

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int studentId) {
        // We need to find the student with this ID and remove them
        // This is a "lambda" expression, a short way to write a loop
        students.removeIf(s -> s.getId() == studentId);
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        String teacherName = (teacher != null) ? teacher.getName() : "None";
        return "Classroom [ID=" + id + ", Name=" + className + ", Teacher=" + teacherName + ", Students Count="
                + students.size() + "]";
    }
}
