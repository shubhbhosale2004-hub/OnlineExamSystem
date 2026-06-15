package com.exam.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Represents an examination with its configuration.
 * Maps to the 'exams' database table.
 */
public class Exam implements Serializable {

    private static final long serialVersionUID = 4L;

    private int examId;
    private String examTitle;
    private int subjectId;
    private String subjectName; // populated via JOIN for display
    private int totalQuestions;
    private int totalMarks;
    private int durationMinutes;
    private double passPercentage;
    private boolean isActive;
    private Timestamp startDate;
    private Timestamp endDate;
    private Timestamp createdAt;

    public Exam() {}

    public Exam(int examId, String examTitle, int subjectId, String subjectName,
                int totalQuestions, int totalMarks, int durationMinutes,
                double passPercentage, boolean isActive, Timestamp startDate,
                Timestamp endDate, Timestamp createdAt) {
        this.examId = examId;
        this.examTitle = examTitle;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.totalQuestions = totalQuestions;
        this.totalMarks = totalMarks;
        this.durationMinutes = durationMinutes;
        this.passPercentage = passPercentage;
        this.isActive = isActive;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
    }

    public int getExamId() { return examId; }
    public void setExamId(int examId) { this.examId = examId; }

    public String getExamTitle() { return examTitle; }
    public void setExamTitle(String examTitle) { this.examTitle = examTitle; }

    public int getSubjectId() { return subjectId; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public int getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(int totalQuestions) { this.totalQuestions = totalQuestions; }

    public int getTotalMarks() { return totalMarks; }
    public void setTotalMarks(int totalMarks) { this.totalMarks = totalMarks; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public double getPassPercentage() { return passPercentage; }
    public void setPassPercentage(double passPercentage) { this.passPercentage = passPercentage; }

    public boolean getIsActive() { return isActive; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    public Timestamp getStartDate() { return startDate; }
    public void setStartDate(Timestamp startDate) { this.startDate = startDate; }

    public Timestamp getEndDate() { return endDate; }
    public void setEndDate(Timestamp endDate) { this.endDate = endDate; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Exam{examId=" + examId + ", examTitle='" + examTitle + "', subjectName='" + subjectName + "'}";
    }
}
