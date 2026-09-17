package org.example.registration.dao;

import org.example.registration.model.EnrollmentModel;
import org.example.registration.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class EnrollmentDao {


    public Optional<EnrollmentModel> saveEnrollment(EnrollmentModel enrollmentModel){
        String sql = "INSERT INTO Enrollment(Student_id, Course_id)" + "VALUES(?,?)";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        )  {
            EnrollmentModel enrollment = new EnrollmentModel();
            stmt.setInt(1, enrollmentModel.getStudentId());
            stmt.setInt(2, enrollmentModel.getCourseId());

            int rowInserted = stmt.executeUpdate();
            if(rowInserted > 0){
                System.out.println("student enrolled successfully");
            }else{
                System.out.println("failed to create course");
                return Optional.empty();
            }

        }catch(SQLException e){
            System.out.println("Database error" + e.getMessage());
        }
        return Optional.ofNullable(enrollmentModel);
    };

    //view student enrollment
    public List<EnrollmentModel> findByStudentId(Integer studentId) {
        List<EnrollmentModel> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM Enrollment WHERE Student_id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    EnrollmentModel enrollment = new EnrollmentModel();
                    enrollment.setId(rs.getInt("Enrollment_id"));
                    enrollment.setStudentId(rs.getInt("Student_id"));
                    enrollment.setCourseId(rs.getInt("Course_id"));
                    enrollments.add(enrollment);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return enrollments;
    }
    public boolean deleteStudentEnrollment(Integer Student_id){
        String sql = "DELETE FROM Enrollment where Student_id = ?";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, Student_id);

            int rowsAffected = stmt.executeUpdate();
            return  rowsAffected > 0;

        }catch(SQLException e){
            System.out.println("Database error: " + e.getMessage());
            return  false;
        }
    }
}