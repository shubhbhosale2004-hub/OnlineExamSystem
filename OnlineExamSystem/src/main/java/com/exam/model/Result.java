package com.exam.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Represents the computed result of a completed exam attempt.
 * Maps to the 'results' database table.
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 8L;

    private int resultId;
    private int attemptId;
    private int studentId;
    private int examId;
    private String studentName; // populated via JOIN
    private String examTitle;   // populated via JOIN
    private int totalQuestions;
    private int attempted;
    private int correctAnswers;
    private int wrongAnswers;
    private int totalMarks;
    private int obtainedMarks;
    private double percentage;
    private String status;
    private Timestamp createdAt;

    public Result() {}

    public Result(int resultId, int attemptId, int studentId, int examId,
                  int totalQuestions, int attempted, int correctAnswers, int wrongAnswers,
                  int totalMarks, int obtainedMarks, double percentage, String status,
                  Timestamp createdAt) {
        this.resultId = resultId;
        this.attemptId = attemptId;
        this.studentId = studentId;
        this.examId = examId;
        this.totalQuestions = totalQuestions;
        this.attempted = attempted;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
        this.totalMarks = totalMarks;
        this.obtainedMarks = obtainedMarks;
        this.percentage = percentage;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getResultId() { return resultId; }
    public void setResultId(int resultId) { this.resultId = resultId; }

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

    public int getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(int totalQuestions) { this.totalQuestions = totalQuestions; }

    public int getAttempted() { return attempted; }
    public void setAttempted(int attempted) { this.attempted = attempted; }

    public int getCorrectAnswers() { return correctAnswers; }
    public void setCorrectAnswers(int correctAnswers) { this.correctAnswers = correctAnswers; }

    public int getWrongAnswers() { return wrongAnswers; }
    public void setWrongAnswers(int wrongAnswers) { this.wrongAnswers = wrongAnswers; }

    public int getTotalMarks() { return totalMarks; }
    public void setTotalMarks(int totalMarks) { this.totalMarks = totalMarks; }

    public int getObtainedMarks() { return obtainedMarks; }
    public void setObtainedMarks(int obtainedMarks) { this.obtainedMarks = obtainedMarks; }

    public double getPercentage() { return percentage; }
    public void setPercentage(double percentage) { this.percentage = percentage; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Result{resultId=" + resultId + ", studentId=" + studentId +
               ", examId=" + examId + ", percentage=" + percentage + ", status='" + status + "'}";
    }
}
