package org.example.registration.model;

import org.example.registration.dto.CourseDto;

import java.util.Objects;

public class CourseModel {
    Integer courseId;
    String courseName;
    String courseCode;
    Integer creditUnit;

    public CourseModel(){};

    public CourseModel(CourseDto courseDto) {
        this.courseName = courseDto.getCourseName();
        this.courseCode = courseDto.getCourseCode();
        this.creditUnit = courseDto.getCreditUnit();
    }


    // CourseModel
    @Override
    public String toString() {
        return "Course ID: " + courseId +
                ", Name: " + courseName +
                ", Code: " + courseCode +
                ", Credit Unit: " + creditUnit;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getCreditUnit() {
        return creditUnit;
    }

    public void setCreditUnit(Integer creditUnit) {
        this.creditUnit = creditUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseModel that = (CourseModel) o;
        return Objects.equals(courseId, that.courseId) && Objects.equals(courseName, that.courseName) && Objects.equals(courseCode, that.courseCode) && Objects.equals(creditUnit, that.creditUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, courseCode, creditUnit);
    }
}
