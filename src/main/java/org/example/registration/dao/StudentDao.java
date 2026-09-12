package org.example.registration.dao;

import org.example.registration.model.StudentModel;
import org.example.registration.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class StudentDao {

    // The StudentDao contains SQL commands for database operations.
    // Business validation belongs in the Service layer.

    public Optional<StudentModel> saveStudent(StudentModel studentModel) {

        String sql = "INSERT INTO Student " +
                "(Student_name, Student_email, Student_department) " +
                "VALUES (?, ?, ?)";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement preparedStmt = conn.prepareStatement(sql)
        ) {

            preparedStmt.setString(1, studentModel.getName());
            preparedStmt.setString(2, studentModel.getEmail());
            preparedStmt.setString(3, studentModel.getDepartment().name());

            int rowInserted = preparedStmt.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("Student created successfully");
            } else {
                System.out.println("Failed to create student");
                return Optional.empty();
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return Optional.empty();
        }

        return Optional.of(studentModel);
    }
}