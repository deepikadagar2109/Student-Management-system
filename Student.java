/**
 * Student.java
 * Represents a single student record.
 * Demonstrates OOP concepts: encapsulation, constructors, getters/setters.
 */
public class Student {

    private int id;
    private String name;
    private int age;
    private String course;
    private double marks;

    // Constructor
    public Student(int id, String name, int age, String course, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
    }

    // ---------- Getters ----------
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    // ---------- Setters ----------
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    /**
     * Converts this student object into a single CSV line for file storage.
     * Format: id,name,age,course,marks
     */
    public String toFileString() {
        return id + "," + name + "," + age + "," + course + "," + marks;
    }

    /**
     * Rebuilds a Student object from a CSV line read from the file.
     */
    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        int age = Integer.parseInt(parts[2].trim());
        String course = parts[3].trim();
        double marks = Double.parseDouble(parts[4].trim());
        return new Student(id, name, age, course, marks);
    }

    /**
     * Nicely formatted row used when printing to the console.
     */
    @Override
    public String toString() {
        return String.format("%-6d %-20s %-5d %-15s %-6.2f", id, name, age, course, marks);
    }
}
