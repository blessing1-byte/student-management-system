package org.example.registration.service;

import org.example.registration.dao.StudentDao;
import org.example.registration.dto.StudentDto;
import org.example.registration.model.StudentModel;
import org.example.registration.util.Response;

import java.util.Optional;

//create the service that adds the student from the addStudent repo
//then pass into the function. the required details to create a dtudent. ie. dto
//        further more create an instance of the type student then pass in the dto
//        the essense of thi is to pass on the id on creation and also lock in tyoe
//        then create an optional that stores te students upon creation
//        edge case:
//        if optional returns empty, retrun a false message
//        but if true, get the obj from the optional and return a true response

        public class StudentService {
    private final StudentDao studentDao = new StudentDao();
    public Response createStudent(StudentDto studentDto){
    StudentModel student = new StudentModel(studentDto);

    Optional<StudentModel> optionalStudent = studentDao.saveStudent(student);
    if(optionalStudent.isEmpty()) {
        return new Response(
                false,
                "something went wrong",
                null
        );
    }
    StudentModel savedStudent = optionalStudent.get();
    return new Response(true,
                        "student created successfully",
                        savedStudent
                        );

}

}
