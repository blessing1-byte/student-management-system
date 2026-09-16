package org.example.registration.service;

import org.example.registration.dao.CourseDao;
import org.example.registration.dao.EnrollmentDao;
import org.example.registration.dao.StudentDao;
import org.example.registration.dto.EnrollmentDto;
import org.example.registration.model.CourseModel;
import org.example.registration.model.EnrollmentModel;
import org.example.registration.model.StudentModel;
import org.example.registration.util.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnrollmentService {
        private final    CourseDao courseDao = new CourseDao();
    private final StudentDao studentDao = new StudentDao();
    private final  EnrollmentDao enrollmentDao = new EnrollmentDao();
    //creation of student enrollment
    public Response  registerStudentToCourse(EnrollmentDto enrollmentDto){

        String studentEmail = enrollmentDto.getStudentEmail();
        String courseCode = enrollmentDto.getCourseCode();

        Optional<StudentModel> student = studentDao.findByEmail(studentEmail);
        Optional<CourseModel> course = courseDao.findByCourseCode(courseCode);

        if(student.isEmpty()){
           return  new Response(false, "student not found", null);
        }  
        StudentModel foundStudent = student.get();

        if(course.isEmpty()){
            return  new Response(false, "course not found", null);
        }
        CourseModel foundCourse = course.get();


        Integer foundStudentId = foundStudent.getId();
        Integer foundCourseId = foundCourse.getCourseId();

        //create a new instance of the entity
        EnrollmentModel enrollment = new EnrollmentModel(foundStudentId, foundCourseId);
        Optional<EnrollmentModel> enrolledStudent = enrollmentDao.saveEnrollment(enrollment);

        if(enrolledStudent.isEmpty()){
            return  new Response(false, "unable to enroll student", null);
        }

        return  new Response(true, "Student enrolled successfully", enrolledStudent);
    };


public Response viewStudentEnrollments(String studentEmail){
    Optional<StudentModel> student = studentDao.findByEmail(studentEmail);
    if (student.isEmpty()){
        return  new Response(false, "student not found", null);
    }
    StudentModel foundStudent = student.get();
    Integer foundId = foundStudent.getId();

    Optional<EnrollmentModel> studentRecords = enrollmentDao.findByStudentId(foundId);
    if(studentRecords.isEmpty()){
        return new Response(false, "Student has no records to show yet.", null);
    }
    return new Response(true, "student enrollment records fetched successfully", studentRecords);

};

public Response deleteStudentEnrollment(String email){
    Optional<StudentModel> foundStudent = studentDao.findByEmail(email);

    if (foundStudent.isEmpty()) {
        return new Response(false, "Cannot delete student that does not exist", null);
    }

    StudentModel existingStudent = foundStudent.get();

    Integer Student_id = existingStudent.getId();


    boolean isDeletedEnrollments = enrollmentDao.deleteStudentEnrollment(Student_id);
    if (!isDeletedEnrollments) {
        return new Response(false, "Failed to delete student enrollments", null);
    }
    return new Response(true, "Student enrollments deleted successfully", null);


}



}
