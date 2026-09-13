package org.example.registration.dto;

import org.example.registration.model.Department;

import java.util.Objects;

public class UpdatesStudentDto {
    private String currentEmail;
    private String name;
    private String email;
    private Department department;

    public UpdatesStudentDto(String currentEmail){
        this.currentEmail = currentEmail;
    }

    public String getCurrentEmail() {
        return currentEmail;
    }

    public void setCurrentEmail(String currentEmail) {
        this.currentEmail = currentEmail;
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
        UpdatesStudentDto that = (UpdatesStudentDto) o;
        return Objects.equals(currentEmail, that.currentEmail) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && department == that.department;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentEmail, name, email, department);
    }
}
