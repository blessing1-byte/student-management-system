package org.example.registration.model;

import org.example.registration.dto.StudentDto;

import java.util.Objects;

public class StudentModel {

    private Integer id;
    private String name;
    private String email;
    private Department department;

    // This is essential for fetch in the repo to prevent it from asking for an argument
    public StudentModel() {}

    public StudentModel(StudentDto studentDto) {
        this.name = studentDto.getName();
        this.email = studentDto.getEmail();
        this.department = studentDto.getDepartment();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

        StudentModel that = (StudentModel) o;

        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(email, that.email)
                && department == that.department;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, department);
    }
}