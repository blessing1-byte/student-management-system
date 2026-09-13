package org.example.registration.ui;

//import org.example.registration.service.CourseService;
//import org.example.registration.service.EnrollmentService;
//import org.example.registration.service.StudentService;

import java.util.Scanner;

public class ConsoleMenu {
//    private final StudentService studentService;
//    private final CourseService courseService;
//    private final EnrollmentService enrollmentService;
    private final Scanner scanner;

    public ConsoleMenu() {
//        this.studentService = new StudentService();
//        this.courseService = new CourseService();
//        this.enrollmentService = new EnrollmentService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = getIntInput("Enter your choice (1-9): ");

            switch (choice) {
                case 1: addStudent(); break;
                case 2: addCourse(); break;
                case 3: registerStudentToCourse(); break;
                case 4: viewStudents(); break;
                case 5: viewCourses(); break;
                case 6: viewStudentEnrollments(); break;
                case 7: updateStudent(); break;
                case 8: deleteRecord(); break;
                case 9:
                    running = false;
                    System.out.println("\nExiting system. Goodbye!");
                    break;
                default:
                    System.out.println("\nInvalid option! Please enter a number between 1 and 9.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Student Registration System ===");
        System.out.println("1. Add Student");
        System.out.println("2. Add Course");
        System.out.println("3. Register Student to Course");
        System.out.println("4. View Students");
        System.out.println("5. View Courses");
        System.out.println("6. View Student Enrollments");
        System.out.println("7. Update Student");
        System.out.println("8. Delete Record");
        System.out.println("9. Exit");
        System.out.println("===================================");
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a valid number.");
            scanner.next();
            System.out.print(prompt);
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Clear newline buffer
        return input;
    }

    private void addStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Department: ");
        String dept = scanner.nextLine();

        // Service call placeholder
        System.out.println("Student registration attempt recorded for: " + name);
    }

    private void addCourse() {
        System.out.println("\n--- Add New Course ---");
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine();
        int credits = getIntInput("Enter Credit Unit: ");

        // Service call placeholder
        System.out.println("Course registration attempt recorded for: " + courseName);
    }

    private void registerStudentToCourse() {
        System.out.println("\n--- Register Student to Course ---");
        int studentId = getIntInput("Enter Student ID: ");
        int courseId = getIntInput("Enter Course ID: ");

        // Service call placeholder
        System.out.println("Enrollment attempt recorded for Student ID " + studentId + " in Course ID " + courseId);
    }

    private void viewStudents() {
        System.out.println("\n--- Viewing All Students ---");
        // Wire to studentService fetch methods
    }

    private void viewCourses() {
        System.out.println("\n--- Viewing All Courses ---");
        // Wire to courseService fetch methods
    }

    private void viewStudentEnrollments() {
        System.out.println("\n--- Viewing Student Enrollments ---");
        int studentId = getIntInput("Enter Student ID: ");
        // Wire to enrollmentService fetch methods
    }

    private void updateStudent() {
        System.out.println("\n--- Update Student Details ---");
        int studentId = getIntInput("Enter Student ID to update: ");
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Email: ");
        String email = scanner.nextLine();

        // Wire to studentService update methods
    }

    private void deleteRecord() {
        System.out.println("\n--- Delete Record ---");
        System.out.println("1. Delete Student");
        System.out.println("2. Delete Course");
        int option = getIntInput("Select record type to delete: ");
        int targetId = getIntInput("Enter ID to delete: ");

        // Wire to DAO/Service delete methods
    }
}
