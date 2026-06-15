package com.exam.controller;

import com.exam.dao.ExamAttemptDAO;
import com.exam.dao.ExamDAO;
import com.exam.dao.QuestionDAO;
import com.exam.model.Exam;
import com.exam.model.ExamAttempt;
import com.exam.model.Question;
import com.exam.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student/start-exam")
public class StartExamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = SessionUtil.getUserId(request);
        int examId;
        try {
            examId = Integer.parseInt(request.getParameter("examId"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/student/exams");
            return;
        }

        ExamDAO examDAO = new ExamDAO();
        ExamAttemptDAO attemptDAO = new ExamAttemptDAO();
        QuestionDAO questionDAO = new QuestionDAO();

        // Block retakes
        if (attemptDAO.hasStudentAttemptedExam(studentId, examId)) {
            request.setAttribute("error", "You have already attempted this exam.");
            response.sendRedirect(request.getContextPath() + "/student/exams");
            return;
        }

        Exam exam = examDAO.getById(examId);
        if (exam == null || !exam.getIsActive()) {
            response.sendRedirect(request.getContextPath() + "/student/exams");
            return;
        }

        // Create a new attempt record
        ExamAttempt attempt = new ExamAttempt();
        attempt.setStudentId(studentId);
        attempt.setExamId(examId);
        int attemptId = attemptDAO.startAttempt(attempt);

        if (attemptId == -1) {
            request.setAttribute("error", "Could not start the exam. Please try again.");
            response.sendRedirect(request.getContextPath() + "/student/exams");
            return;
        }

        List<Question> questions = questionDAO.getQuestionsByExam(examId);

        request.setAttribute("exam", exam);
        request.setAttribute("questions", questions);
        request.setAttribute("attemptId", attemptId);
        request.setAttribute("durationMinutes", exam.getDurationMinutes());
        request.getRequestDispatcher("/WEB-INF/views/student/take-exam.jsp").forward(request, response);
    }
}
