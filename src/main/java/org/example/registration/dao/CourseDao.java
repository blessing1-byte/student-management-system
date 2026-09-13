package org.example.registration.dao;

//import org.example.registration.model.Department;
import org.example.registration.model.CourseModel;
import org.example.registration.util.DatabaseConnection;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CourseDao {
//    the student da represents the sql commands that carries out the functions of:
//    create, read, update and delete
//    it does not contain any logic
//            the service layer holds the business rules
//    the dto defines the schema, ie the data that will be collected from the user

    //create action

    public Optional<CourseModel> saveCourse(CourseModel courseModel){
        //string sql student
        String sql ="INSERT INTO courses(courseName, courseCode, creditUnit)" + "VALUES(?,?,?)";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement preparedStmt = conn.prepareStatement(sql)

        ){

            preparedStmt.setString(1,courseModel.getCourseName());
            preparedStmt.setString(2,courseModel.getCourseCode());
            preparedStmt.setInt(3,courseModel.getCreditUnit());

            //equivalent to result.rows[0]
            int rowInserted = preparedStmt.executeUpdate();
            if(rowInserted > 0){
                System.out.println("course created successfully");
            }else{
                System.out.println("failed to create course");
                return Optional.empty();
            }

        }catch(SQLException e){
            System.out.println("Database error: "+ e.getMessage());
            return Optional.empty();
        }
        return Optional.ofNullable(courseModel);
    };

    //read action
    public List<CourseModel> findAll () {
        List<CourseModel> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultSet = stmt.executeQuery()

        ) {
            //this simple say after executing the query in result set,move to the next line
            while (resultSet.next()) {
                CourseModel course = new CourseModel(); //create model
                course.setCourseId(resultSet.getInt("courseId"));
                course.setCourseName(resultSet.getString("courseName"));
                course.setCourseCode(resultSet.getString("courseCode"));
                course.setCreditUnit(resultSet.getInt("creditUnit"));
                courses.add(course);
            }
            return courses;
        } catch (SQLException e) {
            System.out.println("database error: " + e.getMessage());
        }
        return Collections.emptyList();
    };

    //find by course code
    public Optional<CourseModel> findByCourseCode(String courseCode){
        String sql = "SELECT courseId, courseName, courseCode, creditUnit from courses WHERE courseCode = ?";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            stmt.setString(3, courseCode);
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CourseModel course = new CourseModel();
                    course.setCourseId(rs.getInt("courseId"));
                    course.setCourseName(rs.getString("courseName"));
                    course.setCourseCode(rs.getString("courseCode"));
                    course.setCreditUnit(rs.getInt("creditUnit"));
                    return Optional.of(course);
                }
            }
        }catch(SQLException e){
            System.out.println("Database error: " + e.getMessage());
        }
        return Optional.empty();
    };

    //update action
    public boolean updateCourse(CourseModel courseModel){
        //string sql student
        String sql ="UPDATE courses SET courseName=?, courseCode=?, creditUnit=? WHERE courseId=?";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement preparedStmt = conn.prepareStatement(sql)

        ){
            preparedStmt.setInt(1,courseModel.getCourseId());
            preparedStmt.setString(2,courseModel.getCourseName());
            preparedStmt.setString(3,courseModel.getCourseCode());
            preparedStmt.setInt(4,courseModel.getCreditUnit());


            int rowInserted = preparedStmt.executeUpdate();
            return rowInserted < 0;

        }catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    };

    //delete action
    public boolean deleteCourse(Integer id) {
        String sql = "DELETE FROM courses WHERE i" +
                "courseId = ?";

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
    };

}


