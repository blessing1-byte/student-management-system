package org.example.registration.dto;

import java.util.Objects;

public class UpdatesCourseDto {
    String currentCourseCode;
    String courseName;
    String courseCode;
    Integer creditUnit;

    public UpdatesCourseDto(String currentCourseCode){
        this.currentCourseCode = currentCourseCode;
    }

    public String getCurrentCourseCode() {
        return currentCourseCode;
    }

    public void setCurrentCourseCode(String currentCourseCode) {
        this.currentCourseCode = currentCourseCode;
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
        UpdatesCourseDto that = (UpdatesCourseDto) o;
        return Objects.equals(currentCourseCode, that.currentCourseCode) && Objects.equals(courseName, that.courseName) && Objects.equals(courseCode, that.courseCode) && Objects.equals(creditUnit, that.creditUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentCourseCode, courseName, courseCode, creditUnit);
    }
}
