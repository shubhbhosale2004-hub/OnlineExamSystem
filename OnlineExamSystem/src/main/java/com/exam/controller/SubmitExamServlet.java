package com.exam.controller;

import com.exam.dao.*;
import com.exam.model.*;
import com.exam.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Processes the submitted exam: saves answers, calculates score, generates result.
 */
@WebServlet("/student/submit-exam")
public class SubmitExamServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = SessionUtil.getUserId(request);
        int attemptId, examId;
        try {
            attemptId = Integer.parseInt(request.getParameter("attemptId"));
            examId = Integer.parseInt(request.getParameter("examId"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/student/exams");
            return;
        }

        QuestionDAO questionDAO = new QuestionDAO();
        AnswerDAO answerDAO = new AnswerDAO();
        ExamAttemptDAO attemptDAO = new ExamAttemptDAO();
        ExamDAO examDAO = new ExamDAO();
        ResultDAO resultDAO = new ResultDAO();

        List<Question> questions = questionDAO.getQuestionsByExam(examId);
        Exam exam = examDAO.getById(examId);

        int correctCount = 0;
        int attemptedCount = 0;

        // Process each question's answer
        for (Question question : questions) {
            String paramName = "q_" + question.getQuestionId();
            String selectedOption = request.getParameter(paramName);

            Answer answer = new Answer();
            answer.setAttemptId(attemptId);
            answer.setQuestionId(question.getQuestionId());
            answer.setSelectedOption(selectedOption);

            boolean isCorrect = false;
            if (selectedOption != null && !selectedOption.isEmpty()) {
                attemptedCount++;
                isCorrect = selectedOption.equalsIgnoreCase(question.getCorrectOption());
                if (isCorrect) correctCount++;
            }
            answer.setIsCorrect(isCorrect);
            answerDAO.saveAnswer(answer);
        }

        // Calculate result metrics
        int totalQuestions = questions.size();
        int wrongCount = attemptedCount - correctCount;
        int obtainedMarks = correctCount; // each question is worth 1 mark by default
        int totalMarks = exam.getTotalMarks();
        double percentage = totalQuestions > 0 ? ((double) correctCount / totalQuestions) * 100 : 0;
        String status = percentage >= exam.getPassPercentage() ? "Pass" : "Fail";

        // Determine submission type
        String submitType = request.getParameter("autoSubmit");
        String attemptStatus = "true".equals(submitType) ? "auto_submitted" : "completed";

        // Save the result record
        Result result = new Result();
        result.setAttemptId(attemptId);
        result.setStudentId(studentId);
        result.setExamId(examId);
        result.setTotalQuestions(totalQuestions);
        result.setAttempted(attemptedCount);
        result.setCorrectAnswers(correctCount);
        result.setWrongAnswers(wrongCount);
        result.setTotalMarks(totalMarks);
        result.setObtainedMarks(obtainedMarks);
        result.setPercentage(percentage);
        result.setStatus(status);
        resultDAO.saveResult(result);

        // Mark the attempt as completed
        attemptDAO.completeAttempt(attemptId, attemptStatus);

        response.sendRedirect(request.getContextPath() + "/student/result?attemptId=" + attemptId);
    }
}
