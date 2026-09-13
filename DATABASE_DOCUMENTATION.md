# Student Course Registration System
## Database Documentation

### 1. Database Name

student_management_system

---

### 2. Tables

#### Student
Stores information about registered students.

Fields:
- Student_id
- Student_name
- Student_email
- Student_department

#### Course
Stores information about available courses.

Fields:
- Course_id
- Course_name
- Course_code
- Credit_unit

#### Enrollment
Stores the relationship between students and courses.

Fields:
- Enrollment_id
- Student_id
- Course_id

---

### 3. Primary Keys

- Student.Student_id
- Course.Course_id
- Enrollment.Enrollment_id

All primary key IDs are auto-incremented.

---

### 4. Foreign Keys

Enrollment.Student_id references Student.Student_id.

Enrollment.Course_id references Course.Course_id.

This ensures that an enrollment can only refer to an existing
student and an existing course.

---

### 5. Unique Constraints

Student:
- Student_id is unique.
- Student_email is unique.

Course:
- Course_id is unique.
- Course_code is unique.

Enrollment:
- The combination of Student_id and Course_id is unique.

This prevents the same student from being enrolled in the same
course more than once.

---

### 6. Required Fields

The following fields are NOT NULL:

Student:
- Student_name
- Student_email
- Student_department

Course:
- Course_name
- Course_code
- Credit_unit

Enrollment:
- Student_id
- Course_id

Required fields should also be validated in Java so that empty
strings are rejected before database insertion.

---

### 7. Referential Integrity

The Enrollment table depends on the Student and Course tables.

The foreign keys currently use:

ON DELETE NO ACTION
ON UPDATE NO ACTION

Therefore, a student or course that has existing enrollment
records cannot be deleted until the related enrollment records
are handled.

This prevents orphan enrollment records.

---

### 8. Application Database User

The application uses the dedicated MySQL user:

student_app

The application user has:

- SELECT
- INSERT
- UPDATE
- DELETE

permissions on:

student_management_system.*

The application does not use the MySQL root account.

---

### 9. Database Connection

The Java application connects using JDBC.

Database:

student_management_system

The database password is stored in the environment variable:

STUDENT_DB_PASSWORD

The password is not stored directly in the Java source code.