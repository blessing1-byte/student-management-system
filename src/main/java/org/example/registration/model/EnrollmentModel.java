package org.example.registration.model;

import java.util.Objects;

public class EnrollmentModel {
    Integer id;
    Integer studentId;
    Integer courseId;

    public EnrollmentModel(){};

    public EnrollmentModel(Integer studentId, Integer courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    // EnrollmentModel
    @Override
    public String toString() {
        return "Enrollment ID: " + id +
                ", Student ID: " + studentId +
                ", Course ID: " + courseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentModel that = (EnrollmentModel) o;
        return Objects.equals(id, that.id) && Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, courseId);
    }
}
