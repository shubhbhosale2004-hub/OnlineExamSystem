package com.exam.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Represents an academic subject for categorizing exams.
 * Maps to the 'subjects' database table.
 */
public class Subject implements Serializable {

    private static final long serialVersionUID = 3L;

    private int subjectId;
    private String subjectName;
    private String subjectCode;
    private String description;
    private boolean isActive;
    private Timestamp createdAt;

    public Subject() {}

    public Subject(int subjectId, String subjectName, String subjectCode,
                   String description, boolean isActive, Timestamp createdAt) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.description = description;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public int getSubjectId() { return subjectId; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public String getSubjectCode() { return subjectCode; }
    public void setSubjectCode(String subjectCode) { this.subjectCode = subjectCode; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean getIsActive() { return isActive; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Subject{subjectId=" + subjectId + ", subjectCode='" + subjectCode + "', subjectName='" + subjectName + "'}";
    }
}
