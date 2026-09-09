package org.example.registration.dao;

import org.example.registration.model.StudentModel;

import java.sql.*;
import java.sql.SQLException;
import java.util.Optional;

public class StudentDao {
//    the student da represents the sql commands that carries out the functions of:
//    create, read, update and delete
//    it does not contain any logic
//            the service layer holds the business rules
//    the dto defines the schema, ie the data that will be collected from the user

public Optional<StudentModel> saveStudent(StudentModel studentModel){
    //string sql student
    String sql ="INSERT INTO student(id, name, email, department)" + "VALUES(?,?,?,?)";
    try(
            Connection conn = getConnection();
            PreparedStatement preparedStmt = conn.prepareStatement(sql)

            ){
        preparedStmt.setString(1,studentModel.getId());
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
}
