///**
// * MAD204-01 Java Development for MA - LAB 1
// *
// * Main.java
// *
// * @author [Your Full Name]
// * @studentId [Your Student ID]
// * @date September 19, 2025
// *
// * This is a console application that acts as a Gradebook & Utilities app.
// * It allows users to manage student records, enter grades, and run various
// * utility demos showcasing different Java concepts.
// */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Scanner for user input, declared as static to be accessible in all methods
    private static Scanner scanner = new Scanner(System.in);
    // ArrayList to store Student objects
    private static ArrayList<Student> students = new ArrayList<>();

//    /**
//     * The main entry point of the application.
//     * @param args Command-line arguments (not used in this app).
//     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Gradebook & Utilities App!");
        mainMenu();
    }

//    /**
//     * Displays the main menu and handles user choices in a loop.
//     */
    public static void mainMenu() {
        String choice;
        // Use a do-while loop to ensure the menu is shown at least once
        do {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Add student");
            System.out.println("2. Enter grades");
            System.out.println("3. Show all students");
            System.out.println("4. Utilities");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            // Use a switch statement to handle different menu options
            switch (choice) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    enterGrades();
                    break;
                case "3":
                    showAllStudents();
                    break;
                case "4":
                    utilitiesMenu();
                    break;
                case "5":
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                    break;
            }
        } while (!choice.equals("5")); // The loop continues until the user chooses to exit
    }

//    /**
//     * Prompts the user to add a new student.
//     */
    public static void addStudent() {
        String addAnother;
        // Use a do-while loop to ask if the user wants to add another student
        do {
            System.out.println("\n--- Add New Student ---");
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            int id = getIntInput("Enter student ID: ");

            // Create a new Student object and add it to the ArrayList
            students.add(new Student(name, id));
            System.out.println("Student " + name + " added successfully.");

            System.out.print("Add another student? (y/n): ");
            addAnother = scanner.nextLine().toLowerCase();
        } while (addAnother.equals("y"));
    }

//    /**
//     * Prompts the user to enter grades for a specific student.
//     */
    public static void enterGrades() {
        System.out.println("\n--- Enter Grades ---");
        System.out.print("Enter student name to find: ");
        String nameToFind = scanner.nextLine();

        // Use a for-each loop to find the student
        Student foundStudent = null;
        for (Student s : students) {
            // Use .equals for string comparison, not ==
            if (s.getName().equals(nameToFind)) {
                foundStudent = s;
                break;
            }
        }

        if (foundStudent != null) {
            // Use a standard for loop to iterate 5 times for the 5 grades
            for (int i = 0; i < 5; i++) {
                double grade = getDoubleInput("Enter grade " + (i + 1) + " for " + foundStudent.getName() + ": ");
                foundStudent.setGrade(i, grade);
            }
            System.out.println("Grades updated for " + foundStudent.getName() + ".");
        } else {
            System.out.println("Student not found.");
        }
    }

//    /**
//     * Displays all students in the gradebook.
//     */
    public static void showAllStudents() {
        System.out.println("\n--- All Students ---");
        if (students.isEmpty()) {
            System.out.println("No students in the gradebook yet.");
            return;
        }
        // Use a for-each loop to print details for each student
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

//    /**
//     * Displays the utilities submenu and handles user choices.
//     */
    public static void utilitiesMenu() {
        System.out.println("\n--- Utilities Menu ---");
        System.out.println("1. Operator Precedence Demo");
        System.out.println("2. Type Casting Demo");
        System.out.println("3. Recursion Counter");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        // Use a switch statement for the utilities submenu
        switch (choice) {
            case "1":
                operatorDemo();
                break;
            case "2":
                typeCastingDemo();
                break;
            case "3":
                int start = getIntInput("Enter a number to start the countdown from: ");
                System.out.println("Starting countdown...");
                countdown(start);
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

//    /**
//     * Demonstrates operator precedence (BODMAS).
//     */
    public static void operatorDemo() {
        System.out.println("\n--- Operator Precedence Demo ---");
        System.out.println("2 + 3 * 4 = " + (2 + 3 * 4));
        System.out.println("The result is 14 because multiplication (*) has a higher precedence than addition (+).");
        System.out.println("The calculation is performed as: 2 + (3 * 4) = 2 + 12 = 14.");
        System.out.println("\n(2 + 3) * 4 = " + ((2 + 3) * 4));
        System.out.println("The result is 20 because parentheses () override normal precedence, " +
                "forcing the addition to be performed first: (5) * 4 = 20.");
    }

//    /**
//     * Demonstrates safe widening and narrowing type casting.
//     */
    public static void typeCastingDemo() {
        System.out.println("\n--- Type Casting Demo ---");
        int myInt = 15;
        double myDouble = 15.75;

        // Widening: int to double (safe, no data loss)
        double wideDouble = myInt; // Automatic casting
        System.out.println("Widening: int (" + myInt + ") to double (" + wideDouble + ")");

        // Narrowing: double to int (requires explicit casting, data loss)
        int narrowInt = (int) myDouble; // Explicit casting
        System.out.println("Narrowing: double (" + myDouble + ") to int (" + narrowInt + ")");
        System.out.println("The decimal part is truncated, resulting in data loss.");
    }

//    /**
//     * A recursive method that counts down from a given number.
//     * @param n The number to start the countdown from.
//     */
    public static void countdown(int n) {
        // Base case: guards against negative numbers and stops the recursion at 0
        if (n < 0) {
            System.out.println("Cannot count down from a negative number.");
            return;
        }
        if (n == 0) {
            System.out.println("Done!");
        } else {
            System.out.println(n);
            // Recursive step: call the method again with a decremented value
            countdown(n - 1);
        }
    }

//    /**
//     * Safely reads an integer from the console, handling input mismatches.
//     * @param prompt The message to display to the user.
//     * @return The valid integer entered by the user.
//     */
    public static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                // Clear the buffer after reading the integer
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number.");
                // Consume the invalid input from the scanner to prevent an infinite loop
                scanner.nextLine();
            }
        }
    }

//    /**
//     * Safely reads a double from the console, handling input mismatches.
//     * @param prompt The message to display to the user.
//     * @return The valid double entered by the user.
//     */
    public static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                // Clear the buffer after reading the double
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                // Consume the invalid input from the scanner
                scanner.nextLine();
            }
        }
    }
}