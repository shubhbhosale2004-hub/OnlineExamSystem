package com.exam.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Represents a student's attempt at taking an exam.
 * Maps to the 'exam_attempts' database table.
 */
public class ExamAttempt implements Serializable {

    private static final long serialVersionUID = 7L;

    private int attemptId;
    private int studentId;
    private int examId;
    private String studentName; // populated via JOIN
    private String examTitle;   // populated via JOIN
    private Timestamp startTime;
    private Timestamp endTime;
    private String status;
    private Timestamp createdAt;

    public ExamAttempt() {}

    public ExamAttempt(int attemptId, int studentId, int examId,
                       Timestamp startTime, Timestamp endTime, String status, Timestamp createdAt) {
        this.attemptId = attemptId;
        this.studentId = studentId;
        this.examId = examId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getAttemptId() { return attemptId; }
    public void setAttemptId(int attemptId) { this.attemptId = attemptId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getExamId() { return examId; }
    public void setExamId(int examId) { this.examId = examId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getExamTitle() { return examTitle; }
    public void setExamTitle(String examTitle) { this.examTitle = examTitle; }

    public Timestamp getStartTime() { return startTime; }
    public void setStartTime(Timestamp startTime) { this.startTime = startTime; }

    public Timestamp getEndTime() { return endTime; }
    public void setEndTime(Timestamp endTime) { this.endTime = endTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "ExamAttempt{attemptId=" + attemptId + ", studentId=" + studentId +
               ", examId=" + examId + ", status='" + status + "'}";
    }
}
