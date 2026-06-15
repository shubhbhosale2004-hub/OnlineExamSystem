package com.exam.controller;

import com.exam.dao.ExamAttemptDAO;
import com.exam.dao.ExamDAO;
import com.exam.model.Exam;
import com.exam.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/student/exams")
public class ViewExamsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = SessionUtil.getUserId(request);
        ExamDAO examDAO = new ExamDAO();
        ExamAttemptDAO attemptDAO = new ExamAttemptDAO();

        List<Exam> activeExams = examDAO.getActiveExams();

        // Build a set of exam IDs this student has already attempted
        Set<Integer> attemptedExamIds = new HashSet<>();
        for (Exam exam : activeExams) {
            if (attemptDAO.hasStudentAttemptedExam(studentId, exam.getExamId())) {
                attemptedExamIds.add(exam.getExamId());
            }
        }

        request.setAttribute("exams", activeExams);
        request.setAttribute("attemptedExamIds", attemptedExamIds);
        request.getRequestDispatcher("/WEB-INF/views/student/exams.jsp").forward(request, response);
    }
}
