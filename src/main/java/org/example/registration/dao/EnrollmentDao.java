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
     String sql = "INSERT studentId, courseId into enrollments" + "VALUES(?,?)";
      try(
              Connection conn = DatabaseConnection.getConnection();
              PreparedStatement stmt = conn.prepareStatement(sql);
              )  {
          EnrollmentModel enrollment = new EnrollmentModel();
          stmt.setInt(1, enrollment.getStudentId());
          stmt.setInt(2, enrollment.getCourseId());

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
    public Optional<EnrollmentModel> findByStudentId(Integer studentId){
      List<EnrollmentModel> records = new ArrayList<>();
      String sql = "SELECT id, studentId, courseId from enrollments where studentId =?";

      try(
              Connection conn = DatabaseConnection.getConnection();
              PreparedStatement stmt = conn.prepareStatement(sql)

              ){
          stmt.setInt(2, studentId);
          try(ResultSet rs = stmt.executeQuery()){
              //while there is still a next line. keep looping and setting all the necessary details
             while(rs.next()){
               EnrollmentModel enrollment = new EnrollmentModel();
               enrollment.setId(rs.getInt("id"));
               enrollment.setStudentId(rs.getInt("studentId"));
               enrollment.setCourseId(rs.getInt("courseId"));
               return Optional.of(enrollment);
             }
          }

      } catch (SQLException e) {
          System.out.println("Database Error "+ e.getMessage());
      }

        return Optional.empty();
    };
}
