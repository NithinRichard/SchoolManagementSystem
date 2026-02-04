# College Management System

A simple Java-based Console Application to manage Students, Teachers, and Classes.

## Project Structure
- `com.college.model`: Data classes (Student, Teacher, Classroom)
- `com.college.util`: Helper classes (FileHandler, InputValidator)
- `com.college.interfaces`: Interfaces
- `com.college.main`: Main entry point

## How to Run

### Method 1: Using Batch Scripts (Easiest)
1.  **Build/Compile**: Double-click `build.bat`
2.  **Run**: Double-click `run.bat`

### Method 2: Command Line
```bash
# Compile
javac -d bin com/college/interfaces/*.java com/college/model/*.java com/college/util/*.java com/college/main/*.java

# Run
java -cp bin com.college.main.CollegeManagementSystem
```

## How to Open in IDEs

### VS Code
1.  Open the project folder in VS Code.
2.  Install the "Extension Pack for Java".
3.  Open `CollegeManagementSystem.java` inside `com/college/main`.
4.  Click "Run".

### NetBeans
1.  Go to **File > Open Project**.
2.  Select `NBProject` in this repository.
3.  Run the project (main class is `com.college.main.CollegeManagementSystem`).
4.  **Troubleshooting**:
    *   If you get a **"Connection timed out"** error, it means NetBeans is trying to download the Java feature.
    *   **Fix**: Go to **Tools > Plugins > Installed** tab. Look for **"Java SE"** or **"Java Application"**. Check the box and click **Activate**.
    *   If it is not installed, check your internet connection or proxy settings in **Tools > Options > General > Proxy**.
