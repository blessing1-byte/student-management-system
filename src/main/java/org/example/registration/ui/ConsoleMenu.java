    package org.example.registration.ui;

    //import org.example.registration.service.CourseService;
    //import org.example.registration.service.EnrollmentService;
    //import org.example.registration.service.StudentService;

    import org.example.registration.dto.CourseDto;
    import org.example.registration.dto.EnrollmentDto;
    import org.example.registration.dto.StudentDto;
    import org.example.registration.dto.UpdatesStudentDto;
    import org.example.registration.model.CourseModel;
    import org.example.registration.model.Department;
    import org.example.registration.service.CourseService;
    import org.example.registration.service.EnrollmentService;
    import org.example.registration.service.StudentService;
    import org.example.registration.util.Response;

    import java.util.List;
    import java.util.Scanner;

    public class ConsoleMenu {
        private final StudentService studentService;
        private final CourseService courseService;
        private final EnrollmentService enrollmentService;
        private final Scanner scanner;

        public ConsoleMenu() {
            this.studentService = new StudentService();
            this.courseService = new CourseService();
            this.enrollmentService = new EnrollmentService();
            this.scanner = new Scanner(System.in);
        }

        public void start() {

            boolean running = true;

            while (running) {
                printMenu();
                int choice = getIntInput("Enter your choice (1-10): ");

                switch (choice) {
                    case 1: addStudent(); break;
                    case 2: addCourse(); break;
                    case 3: registerStudentToCourse(); break;
                    case 4: viewStudents(); break;
                    case 5: viewCourses(); break;
                    case 6: viewStudentEnrollments(); break;
                    case 7: updateStudent(); break;
                    case 8: deleteRecord(); break;
                    case 9: deleteEnrollment(); break;
                    case 10:
                        running = false;
                        System.out.println("\nExiting system. Goodbye!");
                        break;
                    default:
                        System.out.println("\nInvalid option! Please enter a number between 1 and 10.");
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
            System.out.println("9. Delete Enrollment");
            System.out.println("10. Exit");
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

        //response handler
        private void handleResponse(Response response) {
            if (response.getIsSuccessful()) {
                System.out.println(response.getMessage());
            } else {
                System.out.println("Failed: " + response.getMessage());
            }
        }

        //list response handler
        private void handleListResponse(Response response) {
            if (!response.getIsSuccessful()) {
                System.out.println("Failed: " + response.getMessage());
                return;
            }

            System.out.println(response.getMessage());

            Object data = response.getData();

            if (!(data instanceof List<?> items)) {
                System.out.println("(no data to display)");
                return;
            }

            if (items.isEmpty()) {
                System.out.println("(no records found)");
                return;
            }

            for (Object item : items) {
                System.out.println(item);
            }
        }

        //create student
        private void addStudent() {
            System.out.println("\n--- Add New Student ---");
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Department (COMPUTER_SCIENCE, ACCOUNTING, ENGINEERING, BUSINESS_ADMINISTRATION): ");
            Department department;
            try {
                String input = scanner.nextLine().toUpperCase();
                department = Department.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid department. Available departments are: " +
                        "COMPUTER_SCIENCE, ACCOUNTING, ENGINEERING & BUSINESS_ADMINISTRATION");
                return;
            }

            StudentDto studentDto = new StudentDto(name, email, department);
            Response response = studentService.addStudent(studentDto);

            handleResponse(response);
        }

        //create course
        private void addCourse() {
            System.out.println("\n--- Add New Course ---");
            System.out.print("Enter Course Name: ");
            String courseName = scanner.nextLine();
            System.out.print("Enter Course Code: ");
            String code = scanner.nextLine();
            int credits = getIntInput("Enter Credit Unit: ");

            // Service call placeholder
            CourseDto courseDto = new CourseDto(courseName, code, credits);
            Response response = courseService.addCourse(courseDto);
            handleResponse(response);

            System.out.println("Course registration attempt recorded for: " + courseName);
        }

        //enroll students
        private void registerStudentToCourse() {
            System.out.println("\n--- Register Student to Course ---");
            System.out.println("Enter Student email: ");
            String studentEmail = scanner.nextLine();
            System.out.println("Enter Course code: ");
            String courseCode = scanner.nextLine();

            // Service call placeholder
            EnrollmentDto enrollmentDto = new EnrollmentDto(studentEmail, courseCode);
            Response response = enrollmentService.registerStudentToCourse(enrollmentDto);
            handleResponse(response);

            System.out.println("Enrollment attempt recorded for Student email : "
                    + studentEmail + " in Course code " + courseCode);
        }

        //view students
        private void viewStudents() {
            System.out.println("\n--- Viewing All Students ---");
            Response response = studentService.viewStudents();
            handleListResponse(response);
        }

        //view courses
        private void viewCourses() {
            System.out.println("\n--- Viewing All Courses ---");
            // Wire to courseService fetch methods
            Response response = courseService.viewCourses();
            handleListResponse(response);
        }

        //view student records
        private void viewStudentEnrollments() {
            System.out.println("\n--- Viewing Student Enrollments ---");
            System.out.println("Enter Student Email: ");
            String studentEmail = scanner.nextLine();
            // Wire to enrollmentService fetch methods
            Response response = enrollmentService.viewStudentEnrollments(studentEmail);
            handleListResponse(response);
        }


        private void updateStudent() {
            System.out.println("\n--- Update Student Details ---");
            System.out.print("Enter Student email to update: ");
            String studentEmail = scanner.nextLine();

            UpdatesStudentDto updatesStudentDto = new UpdatesStudentDto(studentEmail);

            System.out.print("Enter New Name (leave blank to keep current): ");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                updatesStudentDto.setName(name);
            }

            System.out.print("Enter New Email (leave blank to keep current): ");
            String email = scanner.nextLine();
            if (!email.trim().isEmpty()) {
                updatesStudentDto.setEmail(email);
            }

            System.out.print("Enter New Department (leave blank to keep current): ");
            String deptInput = scanner.nextLine();
            if (!deptInput.trim().isEmpty()) {
                try {
                    Department department = Department.valueOf(deptInput.toUpperCase());
                    updatesStudentDto.setDepartment(department);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid department entered. Update cancelled.");
                    return;
                }
            }

            Response response = studentService.updateStudent(updatesStudentDto);
            handleResponse(response);
        }

        //delete record
        private void deleteRecord() {
            System.out.println("\n--- Delete Record ---");
            System.out.println("1. Delete Student");
            System.out.println("2. Delete Course");
            int option = getIntInput("Select record type to delete: ");

            if (option == 1) {
                System.out.print("Enter student email to delete: ");
                String email = scanner.nextLine();
                Response response = studentService.deleteStudent(email);
                handleResponse(response);

            } else if (option == 2) {
                System.out.print("Enter course code to delete: ");
                String courseCode = scanner.nextLine();
                Response response = courseService.deleteCourse(courseCode);
                handleResponse(response);

            } else {
                System.out.println("Invalid option selected.");
            }
        }

        //delete enrollment
        private void deleteEnrollment() {
            System.out.println("\n--- Delete Enrollment ---");
            System.out.print("Enter student email: ");
            String studentEmail = scanner.nextLine();

            Response response = enrollmentService.deleteStudentEnrollment(studentEmail);
            handleResponse(response);
        }

    }