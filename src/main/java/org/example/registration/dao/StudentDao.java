package org.example.registration.dao;

import org.example.registration.model.Department;
import org.example.registration.model.StudentModel;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StudentDao {
//    the student da represents the sql commands that carries out the functions of:
//    create, read, update and delete
//    it does not contain any logic
//            the service layer holds the business rules
//    the dto defines the schema, ie the data that will be collected from the user

    //create action
public Optional<StudentModel> saveStudent(StudentModel studentModel){
    //string sql student
    String sql ="INSERT INTO student(id, name, email, department)" + "VALUES(?,?,?,?)";
    try(
            Connection conn = getConnection();
            PreparedStatement preparedStmt = conn.prepareStatement(sql)

            ){
        preparedStmt.setInt(1,studentModel.getId());
        preparedStmt.setString(2,studentModel.getName());
        preparedStmt.setString(3,studentModel.getEmail());
        preparedStmt.setString(4,studentModel.getDepartment().name());

        //equivalent to result.rows[0]
        int rowInserted = preparedStmt.executeUpdate();
        if(rowInserted > 0){
            System.out.println("student created successfully");
        }else{
            System.out.println("failed to create student");
           return Optional.empty();
        }

    }catch(SQLException e){
        System.out.println("Database error: "+ e.getMessage());
return Optional.empty();
    }
    return Optional.ofNullable(studentModel);
};

//read action
    public List<StudentModel> findAll () {
        List<StudentModel> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultSet = stmt.executeQuery()

        ) {
            //this simple say after executing the query in result set,move to the next line
            while (resultSet.next()) {
                StudentModel student = new StudentModel(); //create model
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setDepartment(Department.valueOf(resultSet.getString("department")));
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            System.out.println("database error: " + e.getMessage());
        }
        return Collections.emptyList();
    };

    //find by email
    public Optional<StudentModel> findByEmail(String email){
        String sql = "SELECT id, name, email, department from user WHERE id = ?";
        try(
                Connection conn = getConnection;
                PreparedStatement stmt = conn.prepareStatement(sql);
                )
        {
            stmt.setString(3, email);
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    StudentModel student = new StudentModel();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setEmail(rs.getString("email"));
                    student.setDepartment(Department.valueOf(rs.getString("department")));
                    return Optional.of(student);
                }
            }
        }catch(SQLException e){
            System.out.println("Database error: " + e.getMessage());
        }


        return Optional.empty();
    };

    //update action
    public boolean updateStudent(StudentModel studentModel){
        //string sql student
        String sql ="UPDATE student SET name=?, email=?, department=? WHERE id=?";
        try(
                Connection conn = getConnection();
                PreparedStatement preparedStmt = conn.prepareStatement(sql)

        ){
            preparedStmt.setInt(1,studentModel.getId());
            preparedStmt.setString(2,studentModel.getName());
            preparedStmt.setString(3,studentModel.getEmail());
            preparedStmt.setString(4,studentModel.getDepartment().name());


            int rowInserted = preparedStmt.executeUpdate();
                return rowInserted < 0;

        }catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    };

    //delete action
    public boolean deleteStudent(Integer id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement preparedStmt = conn.prepareStatement(sql)
        ) {
            preparedStmt.setString(1, id);
            int rowsAffected = preparedStmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    };

}


