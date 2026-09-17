import java.util.Scanner;

/**
 * Main.java
 * Entry point of the Student Management System.
 * Displays the menu and routes user choices to StudentManager methods.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        boolean running = true;

        System.out.println("=========================================");
        System.out.println("   WELCOME TO STUDENT MANAGEMENT SYSTEM");
        System.out.println("=========================================");

        while (running) {
            printMenu();
            int choice = readInt(sc, "Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        addStudentFlow(sc, manager);
                        break;
                    case 2:
                        manager.viewAllStudents();
                        break;
                    case 3:
                        int searchId = readInt(sc, "Enter student ID to search: ");
                        manager.searchStudent(searchId);
                        break;
                    case 4:
                        updateStudentFlow(sc, manager);
                        break;
                    case 5:
                        int deleteId = readInt(sc, "Enter student ID to delete: ");
                        manager.deleteStudent(deleteId);
                        break;
                    case 6:
                        System.out.println("Data saved. Exiting... Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice! Please select a valid option (1-6).");
                }
            } catch (Exception e) {
                // Catch-all safety net so the program never crashes on bad input
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n----------- MENU -----------");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.println("-----------------------------");
    }

    private static void addStudentFlow(Scanner sc, StudentManager manager) {
        int id = readInt(sc, "Enter Student ID: ");
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        int age = readInt(sc, "Enter Student Age: ");
        System.out.print("Enter Course: ");
        String course = sc.nextLine();
        double marks = readDouble(sc, "Enter Marks: ");

        Student student = new Student(id, name, age, course, marks);
        manager.addStudent(student);
    }

    private static void updateStudentFlow(Scanner sc, StudentManager manager) {
        int id = readInt(sc, "Enter Student ID to update: ");
        System.out.print("Enter New Name: ");
        String name = sc.nextLine();
        int age = readInt(sc, "Enter New Age: ");
        System.out.print("Enter New Course: ");
        String course = sc.nextLine();
        double marks = readDouble(sc, "Enter New Marks: ");

        manager.updateStudent(id, name, age, course, marks);
    }

    /**
     * Safely reads an integer from the user, re-prompting on invalid input.
     * Demonstrates exception handling for non-numeric entries.
     */
    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(sc.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a whole number.");
            }
        }
    }

    /**
     * Safely reads a double from the user, re-prompting on invalid input.
     */
    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(sc.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
}
