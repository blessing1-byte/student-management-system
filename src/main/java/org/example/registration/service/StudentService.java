package org.example.registration.service;

import org.example.registration.dao.StudentDao;
import org.example.registration.dto.StudentDto;
import org.example.registration.dto.UpdatesStudentDto;
import org.example.registration.model.Department;
import org.example.registration.model.StudentModel;
import org.example.registration.util.Response;

import java.util.List;
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
            //holds the repo methods
            private final StudentDao studentDao = new StudentDao();

    //first service: create student
    public Response createStudent(StudentDto studentDto){

        String name = studentDto.getName();
        String email = studentDto.getEmail();

        //validation
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")){
            throw new IllegalArgumentException("invalid email. try again");
        }
        if(studentDto.getDepartment() == null){
            throw new IllegalArgumentException("department was not selected for student");
        }

        //only build the model once the dto is valid -- ie creating an instance
        StudentModel student = new StudentModel(studentDto);

    Optional<StudentModel> optionalStudent = studentDao.saveStudent(student);
       if(optionalStudent.isEmpty()) {
           return new Response(false,"something went wrong",null);
      }

    StudentModel savedStudent = optionalStudent.get();
       return new Response(true,"student created successfully",savedStudent);

}

    //second service: read student
    public Response readStudents(){
        List<StudentModel> students = studentDao.findAll();
        if (students.isEmpty()){
            return new Response(false, "cannot fetch students", null);
        }
        return new Response(true,"student fetched successfully",students);

    }

    //third service update
    public Response updateStudent(UpdatesStudentDto updateDto) {
        Optional<StudentModel> foundStudent = studentDao.findByEmail(updateDto.getCurrentEmail());

        if (foundStudent.isEmpty()) {
            return new Response(false, "Student does not exist", null);
        }

        StudentModel existingStudent = foundStudent.get();

        // empty input keeps old value
        String newName = (updateDto.getName() == null || updateDto.getName().trim().isEmpty())
                ? existingStudent.getName()
                : updateDto.getName();

        String newEmail = (updateDto.getEmail() == null || updateDto.getEmail().trim().isEmpty())
                ? existingStudent.getEmail()
                : updateDto.getEmail();

        Department newDepartment = (updateDto.getDepartment() == null)
                ? existingStudent.getDepartment()
                : updateDto.getDepartment();

        // validation on the FINAL merged values
        if (newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (!newEmail.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
            throw new IllegalArgumentException("Invalid email. Try again");
        }
        if (newDepartment == null) {
            throw new IllegalArgumentException("Department was not selected for student");
        }

        // build updated model using the EXISTING id — never a freshly generated one
        StudentModel updatedStudent = new StudentModel();
        updatedStudent.setId(existingStudent.getId());
        updatedStudent.setName(newName);
        updatedStudent.setEmail(newEmail);
        updatedStudent.setDepartment(newDepartment);

        boolean isUpdated = studentDao.updateStudent(updatedStudent);

        if (!isUpdated) {
            return new Response(false, "Failed to update student", null);
        }

        return new Response(true, "Student updated successfully", updatedStudent);
    }


    public Response deleteStudent(String email) {
        Optional<StudentModel> foundStudent = studentDao.findByEmail(email);

        if (foundStudent.isEmpty()) {
            return new Response(false, "Cannot delete student that does not exist", null);
        }

        StudentModel existingStudent = foundStudent.get();

        boolean isDeleted = studentDao.deleteStudent(existingStudent.getId());

        if (!isDeleted) {
            return new Response(false, "Failed to delete student", null);
        }

        return new Response(true, "Student deleted successfully", null);
    }



}
