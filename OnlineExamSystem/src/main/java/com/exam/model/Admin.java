package com.exam.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Represents an administrator account in the examination system.
 * Maps to the 'admin' database table.
 */
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    private int adminId;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private Timestamp createdAt;

    /** Default no-argument constructor */
    public Admin() {
    }

    /** Parameterized constructor with all fields */
    public Admin(int adminId, String username, String password, String fullName,
                 String email, Timestamp createdAt) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = createdAt;
    }

    // ---- Getters and Setters ----

    public int getAdminId() { return adminId; }
    public void setAdminId(int adminId) { this.adminId = adminId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Admin{adminId=" + adminId + ", username='" + username + "', fullName='" + fullName + "'}";
    }
}
