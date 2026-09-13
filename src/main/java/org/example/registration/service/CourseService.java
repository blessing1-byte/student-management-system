package org.example.registration.service;

import org.example.registration.dao.CourseDao;
import org.example.registration.dto.CourseDto;
import org.example.registration.dto.UpdatesCourseDto;
import org.example.registration.model.CourseModel;
import org.example.registration.util.Response;

import java.util.List;
import java.util.Optional;

public class CourseService {
    //holds the repo methods
    private final CourseDao courseDao = new CourseDao();

    //first service: create course
    public Response createCourse(CourseDto courseDto) {

        String name = courseDto.getCourseName();
        String courseCode = courseDto.getCourseCode();
        Integer creditUnit = courseDto.getCreditUnit();

        //validation
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty");
        }
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be empty");
        }
        if (creditUnit == null) {
            throw new IllegalArgumentException("Credit unit was not provided for course");
        }

        //only build the model once the dto is valid -- ie creating an instance
        CourseModel course = new CourseModel(courseDto);

        Optional<CourseModel> optionalCourse = courseDao.saveCourse(course);
        if (optionalCourse.isEmpty()) {
            return new Response(false, "something went wrong", null);
        }

        CourseModel savedCourse = optionalCourse.get();
        return new Response(true, "course created successfully", savedCourse);
    }

    //second service: read course
    public Response readCourse() {
        List<CourseModel> courses = courseDao.findAll();
        if (courses.isEmpty()) {
            return new Response(false, "cannot fetch courses", null);
        }
        return new Response(true, "courses fetched successfully", courses);
    }

    //third service: update course
    public Response updateCourse(UpdatesCourseDto updateDto) {
        Optional<CourseModel> foundCourse = courseDao.findByCourseCode(updateDto.getCurrentCourseCode());

        if (foundCourse.isEmpty()) {
            return new Response(false, "Course does not exist", null);
        }

        CourseModel existingCourse = foundCourse.get();

        // empty input keeps old value
        String newName = (updateDto.getCourseName() == null || updateDto.getCourseName().trim().isEmpty())
                ? existingCourse.getCourseName()
                : updateDto.getCourseName();

        String newCourseCode = (updateDto.getCourseCode() == null || updateDto.getCourseCode().trim().isEmpty())
                ? existingCourse.getCourseCode()
                : updateDto.getCourseCode();

        Integer newCreditUnit = (updateDto.getCreditUnit() == null)
                ? existingCourse.getCreditUnit()
                : updateDto.getCreditUnit();

        // validation on the FINAL merged values
        if (newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty");
        }
        if (newCourseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be empty");
        }
        if (newCreditUnit == null) {
            throw new IllegalArgumentException("Credit unit was not provided for course");
        }

        // build updated model using the EXISTING id — never a freshly generated one
        CourseModel updatedCourse = new CourseModel();
        updatedCourse.setCourseId(existingCourse.getCourseId());
        updatedCourse.setCourseName(newName);
        updatedCourse.setCourseCode(newCourseCode);
        updatedCourse.setCreditUnit(newCreditUnit);

        boolean isUpdated = courseDao.updateCourse(updatedCourse);

        if (!isUpdated) {
            return new Response(false, "Failed to update course", null);
        }

        return new Response(true, "Course updated successfully", updatedCourse);
    }

    //fourth service: delete course
    public Response deleteCourse(String courseCode) {
        Optional<CourseModel> foundCourse = courseDao.findByCourseCode(courseCode);

        if (foundCourse.isEmpty()) {
            return new Response(false, "Cannot delete course that does not exist", null);
        }

        CourseModel existingCourse = foundCourse.get();

        boolean isDeleted = courseDao.deleteCourse(existingCourse.getCourseId());

        if (!isDeleted) {
            return new Response(false, "Failed to delete course", null);
        }

        return new Response(true, "Course deleted successfully", null);
    }
}