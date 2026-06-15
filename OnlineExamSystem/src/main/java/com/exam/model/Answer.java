package com.exam.model;

import java.io.Serializable;

/**
 * Represents a student's answer to a single question in an exam attempt.
 * Maps to the 'answers' database table.
 */
public class Answer implements Serializable {

    private static final long serialVersionUID = 6L;

    private int answerId;
    private int attemptId;
    private int questionId;
    private String selectedOption;
    private boolean isCorrect;

    public Answer() {}

    public Answer(int answerId, int attemptId, int questionId,
                  String selectedOption, boolean isCorrect) {
        this.answerId = answerId;
        this.attemptId = attemptId;
        this.questionId = questionId;
        this.selectedOption = selectedOption;
        this.isCorrect = isCorrect;
    }

    public int getAnswerId() { return answerId; }
    public void setAnswerId(int answerId) { this.answerId = answerId; }

    public int getAttemptId() { return attemptId; }
    public void setAttemptId(int attemptId) { this.attemptId = attemptId; }

    public int getQuestionId() { return questionId; }
    public void setQuestionId(int questionId) { this.questionId = questionId; }

    public String getSelectedOption() { return selectedOption; }
    public void setSelectedOption(String selectedOption) { this.selectedOption = selectedOption; }

    public boolean getIsCorrect() { return isCorrect; }
    public void setIsCorrect(boolean isCorrect) { this.isCorrect = isCorrect; }

    @Override
    public String toString() {
        return "Answer{answerId=" + answerId + ", questionId=" + questionId +
               ", selectedOption='" + selectedOption + "', isCorrect=" + isCorrect + "}";
    }
}
