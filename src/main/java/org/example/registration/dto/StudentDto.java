package org.example.registration.dto;

import org.example.registration.model.Department;

import java.util.Objects;

public class StudentDto {
    private String name;
    private String email;
    private Department department;

    public StudentDto(String name, String email, Department department) {
        this.name = name;
        this.email = email;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDto that = (StudentDto) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && department == that.department;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, department);
    }
}
