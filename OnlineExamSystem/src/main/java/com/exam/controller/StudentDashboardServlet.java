package com.exam.controller;

import com.exam.dao.*;
import com.exam.model.Result;
import com.exam.model.Student;
import com.exam.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student/dashboard")
public class StudentDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = SessionUtil.getUserId(request);

        StudentDAO studentDAO = new StudentDAO();
        ExamDAO examDAO = new ExamDAO();
        ExamAttemptDAO attemptDAO = new ExamAttemptDAO();
        ResultDAO resultDAO = new ResultDAO();

        Student student = studentDAO.getById(studentId);
        int availableExams = examDAO.getActiveExams().size();
        int completedExams = attemptDAO.getAttemptsByStudent(studentId).size();
        List<Result> allResults = resultDAO.getResultsByStudent(studentId);

        // Calculate average score across all results
        double avgScore = 0;
        if (!allResults.isEmpty()) {
            double totalPct = 0;
            for (Result r : allResults) totalPct += r.getPercentage();
            avgScore = totalPct / allResults.size();
        }

        // Only show the 5 most recent results on the dashboard
        List<Result> recentResults = allResults.size() > 5 ? allResults.subList(0, 5) : allResults;

        request.setAttribute("student", student);
        request.setAttribute("availableExamsCount", availableExams);
        request.setAttribute("completedExamsCount", completedExams);
        request.setAttribute("averageScore", String.format("%.1f", avgScore));
        request.setAttribute("totalResults", allResults.size());
        request.setAttribute("recentResults", recentResults);

        request.getRequestDispatcher("/WEB-INF/views/student/dashboard.jsp").forward(request, response);
    }
}
