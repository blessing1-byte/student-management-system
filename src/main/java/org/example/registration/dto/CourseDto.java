package org.example.registration.dto;

import java.util.Objects;

public class CourseDto {
    String courseName;
    String courseCode;
    Integer creditUnit;

    public CourseDto(String courseName, String courseCode, Integer creditUnit) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditUnit = creditUnit;
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
        CourseDto courseDto = (CourseDto) o;
        return creditUnit == courseDto.creditUnit && Objects.equals(courseName, courseDto.courseName) && Objects.equals(courseCode, courseDto.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, courseCode, creditUnit);
    }
}
