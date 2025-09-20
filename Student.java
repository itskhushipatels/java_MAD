//MAD204 Java Development for MA - LAB 1
//  Student.java
// name-KHUSHI PATEL
// id-A00198843
// This class models a student with a name, ID, and an array of grades.
//It provides methods to manage grades, calculate the average, and determine a letter grade.
public class Student {

    // Fields
    private String name;
    private int id;
    private double[] grades;

//
//     Constructs a new Student object.
//     @param name The student's name.
//      @param id The student's ID.
//
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.grades = new double[5]; // Initialize grades array with size 5
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

//
//      Sets a grade at a specific index.
//      @param index The index (0-4) in the grades array.
//      @param value The grade value to set.
//
    public void setGrade(int index, double value) {
        // Ensure the index is within the valid range
        if (index >= 0 && index < grades.length) {
            this.grades[index] = value;
        } else {
            System.out.println("Invalid grade index. Must be between 0 and 4.");
        }
    }

//
//      Calculates the average of all grades for the student.
//      @return The average grade as a double.
//
    public double average() {
        double sum = 0;
        // Use a for-each loop to iterate over the grades array
        for (double grade : this.grades) {
            sum += grade;
        }
        // Avoid division by zero if no grades have been entered
        return this.grades.length > 0 ? sum / this.grades.length : 0.0;
    }

//
//      Determines the letter grade based on the average grade.
//      @return A character representing the letter grade (A, B, C, D, or F).
//
    public char letterGrade() {
        double avg = average();
        // Use if/else if to determine the letter grade based on ranges
        if (avg >= 90) {
            return 'A';
        } else if (avg >= 80) {
            return 'B';
        } else if (avg >= 70) {
            return 'C';
        } else if (avg >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

//
//      Provides a string representation of the Student object.
//      @return A formatted string showing the student's ID, name, average grade, and letter grade.
//
    @Override
    public String toString() {
        return "ID: " + this.id + ", Name: " + this.name + ", Avg: " + String.format("%.2f", average()) + " (" + letterGrade() + ")";
    }
}

