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
        String sql ="INSERT INTO Course(Course_name, Course_code, Credit_unit)" + "VALUES(?,?,?)";
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
        String sql = "SELECT * FROM Course";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultSet = stmt.executeQuery()

        ) {
            //this simple say after executing the query in result set,move to the next line
            while (resultSet.next()) {
                CourseModel course = new CourseModel(); //create model
                course.setCourseId(resultSet.getInt("Course_id"));
                course.setCourseName(resultSet.getString("Course_name"));
                course.setCourseCode(resultSet.getString("Course_code"));
                course.setCreditUnit(resultSet.getInt("Credit_unit"));
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
        String sql = "SELECT Course_id, Course_name, Course_code, Credit_unit " +
                "FROM Course WHERE Course_code = ?";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
            stmt.setString(1, courseCode);
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CourseModel course = new CourseModel();
                    course.setCourseId(rs.getInt("Course_id"));
                    course.setCourseName(rs.getString("Course_name"));
                    course.setCourseCode(rs.getString("Course_code"));
                    course.setCreditUnit(rs.getInt("Credit_unit"));
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
        String sql ="UPDATE Course SET Course_name=?, Course_code=?, Credit_unit=? WHERE Course_id=?";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement preparedStmt = conn.prepareStatement(sql)

        ){
            preparedStmt.setString(1,courseModel.getCourseName());
            preparedStmt.setString(2,courseModel.getCourseCode());
            preparedStmt.setInt(3,courseModel.getCreditUnit());
            preparedStmt.setInt(4,courseModel.getCourseId());


            int rowInserted = preparedStmt.executeUpdate();
            return rowInserted > 0;

        }catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    };

    //delete action
    public boolean deleteCourse(Integer id) {
        String sql = "DELETE FROM Course WHERE Course_id = ?";

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