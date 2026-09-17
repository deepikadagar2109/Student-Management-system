import java.io.*;
import java.util.ArrayList;

/**
 * StudentManager.java
 * Handles all core operations: add, view, search, update, delete,
 * plus saving/loading data to and from a file.
 */
public class StudentManager {

    private ArrayList<Student> studentList;
    private static final String FILE_NAME = "students.txt";

    public StudentManager() {
        studentList = new ArrayList<>();
        loadFromFile();   // load existing data (if any) when the program starts
    }

    // ---------------------------------------------------------
    // ADD
    // ---------------------------------------------------------
    public void addStudent(Student s) {
        // Prevent duplicate IDs
        if (findStudentById(s.getId()) != null) {
            System.out.println("A student with ID " + s.getId() + " already exists!");
            return;
        }
        studentList.add(s);
        saveToFile();
        System.out.println("Student added successfully!");
    }

    // ---------------------------------------------------------
    // VIEW
    // ---------------------------------------------------------
    public void viewAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("\n" + "-".repeat(60));
        System.out.printf("%-6s %-20s %-5s %-15s %-6s%n", "ID", "Name", "Age", "Course", "Marks");
        System.out.println("-".repeat(60));
        for (Student s : studentList) {
            System.out.println(s);
        }
        System.out.println("-".repeat(60));
    }

    // ---------------------------------------------------------
    // SEARCH
    // ---------------------------------------------------------
    public Student findStudentById(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public void searchStudent(int id) {
        Student s = findStudentById(id);
        if (s == null) {
            System.out.println("No student found with ID: " + id);
        } else {
            System.out.println("\nStudent Found:");
            System.out.printf("%-6s %-20s %-5s %-15s %-6s%n", "ID", "Name", "Age", "Course", "Marks");
            System.out.println(s);
        }
    }

    // ---------------------------------------------------------
    // UPDATE
    // ---------------------------------------------------------
    public void updateStudent(int id, String name, int age, String course, double marks) {
        Student s = findStudentById(id);
        if (s == null) {
            System.out.println("No student found with ID: " + id);
            return;
        }
        s.setName(name);
        s.setAge(age);
        s.setCourse(course);
        s.setMarks(marks);
        saveToFile();
        System.out.println("Student record updated successfully!");
    }

    // ---------------------------------------------------------
    // DELETE
    // ---------------------------------------------------------
    public void deleteStudent(int id) {
        Student s = findStudentById(id);
        if (s == null) {
            System.out.println("No student found with ID: " + id);
            return;
        }
        studentList.remove(s);
        saveToFile();
        System.out.println("Student record deleted successfully!");
    }

    // ---------------------------------------------------------
    // FILE HANDLING
    // ---------------------------------------------------------

    /** Saves the current list of students to students.txt */
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : studentList) {
                writer.write(s.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    /** Loads student data from students.txt into the ArrayList, if the file exists */
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // Nothing to load on first run
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    try {
                        studentList.add(Student.fromFileString(line));
                    } catch (Exception e) {
                        System.out.println("Skipping corrupted record: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
        }
    }
}
