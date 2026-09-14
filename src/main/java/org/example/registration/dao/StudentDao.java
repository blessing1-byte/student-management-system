package org.example.registration.dao;

import org.example.registration.model.Department;
import org.example.registration.model.StudentModel;
import org.example.registration.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StudentDao {

    // The StudentDao contains SQL commands for database operations.
    // Business validation belongs in the Service layer.

<<<<<<< HEAD
    // CREATE
    public Optional<StudentModel> saveStudent(StudentModel studentModel) {

        String sql = "INSERT INTO Student " +
                "(Student_name, Student_email, Student_department) " +
                "VALUES (?, ?, ?)";
=======
public Optional<StudentModel> saveStudent(StudentModel studentModel){
    //string sql student
    String sql ="INSERT INTO student(name, email, department)" + "VALUES(?,?,?)";
    try(
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement preparedStmt = conn.prepareStatement(sql)

            ){
        preparedStmt.setString(1,studentModel.getName());
        preparedStmt.setString(2,studentModel.getEmail());
        preparedStmt.setString(3,studentModel.getDepartment().name());
>>>>>>> origin/main

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement preparedStmt = conn.prepareStatement(sql)
        ) {

            preparedStmt.setString(1, studentModel.getName());
            preparedStmt.setString(2, studentModel.getEmail());
            preparedStmt.setString(3, studentModel.getDepartment().name());

            int rowsInserted = preparedStmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Student created successfully");
                return Optional.of(studentModel);
            } else {
                System.out.println("Failed to create student");
                return Optional.empty();
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return Optional.empty();
        }
    }

    // READ ALL
    public List<StudentModel> findAll() {

        List<StudentModel> students = new ArrayList<>();

        String sql = "SELECT * FROM Student";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultSet = stmt.executeQuery()
        ) {

            while (resultSet.next()) {

                StudentModel student = new StudentModel();

                student.setId(resultSet.getInt("Student_id"));
                student.setName(resultSet.getString("Student_name"));
                student.setEmail(resultSet.getString("Student_email"));
                student.setDepartment(
                        Department.valueOf(
                                resultSet.getString("Student_department")
                        )
                );

                students.add(student);
            }

<<<<<<< HEAD
            return students;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // FIND BY EMAIL
    public Optional<StudentModel> findByEmail(String email) {

        String sql = "SELECT Student_id, Student_name, Student_email, " +
                "Student_department FROM Student WHERE Student_email = ?";

        try (
=======
    //find by email
    public Optional<StudentModel> findByEmail(String email){
        String sql = "SELECT id, name, email, department from user WHERE email = ?";
        try(
>>>>>>> origin/main
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    StudentModel student = new StudentModel();

                    student.setId(rs.getInt("Student_id"));
                    student.setName(rs.getString("Student_name"));
                    student.setEmail(rs.getString("Student_email"));
                    student.setDepartment(
                            Department.valueOf(
                                    rs.getString("Student_department")
                            )
                    );

                    return Optional.of(student);
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return Optional.empty();
    }

    // UPDATE
    public boolean updateStudent(StudentModel studentModel) {

        String sql = "UPDATE Student SET " +
                "Student_name = ?, " +
                "Student_email = ?, " +
                "Student_department = ? " +
                "WHERE Student_id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement preparedStmt = conn.prepareStatement(sql)
        ) {

            preparedStmt.setString(1, studentModel.getName());
            preparedStmt.setString(2, studentModel.getEmail());
            preparedStmt.setString(3, studentModel.getDepartment().name());
            preparedStmt.setInt(4, studentModel.getId());

            int rowsUpdated = preparedStmt.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    // DELETE
    public boolean deleteStudent(Integer id) {

        String sql = "DELETE FROM Student WHERE Student_id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement preparedStmt = conn.prepareStatement(sql)
        ) {

            preparedStmt.setInt(1, id);

            int rowsAffected = preparedStmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }
}