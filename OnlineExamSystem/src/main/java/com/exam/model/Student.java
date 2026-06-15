package com.exam.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Represents a student enrolled in the examination system.
 * Maps to the 'students' database table.
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 2L;

    private int studentId;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String gender;
    private Date dob;
    private String address;
    private boolean isActive;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Student() {
    }

    public Student(int studentId, String username, String password, String fullName,
                   String email, String phone, String gender, Date dob, String address,
                   boolean isActive, Timestamp createdAt, Timestamp updatedAt) {
        this.studentId = studentId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public boolean getIsActive() { return isActive; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Student{studentId=" + studentId + ", username='" + username + "', fullName='" + fullName + "'}";
    }
}
