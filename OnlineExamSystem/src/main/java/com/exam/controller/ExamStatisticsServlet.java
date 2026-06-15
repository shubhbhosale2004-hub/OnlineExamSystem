package com.exam.controller;

import com.exam.dao.ExamDAO;
import com.exam.dao.ResultDAO;
import com.exam.model.Exam;
import com.exam.model.Result;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/exam-stats")
public class ExamStatisticsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int examId = Integer.parseInt(request.getParameter("examId"));
        ExamDAO examDAO = new ExamDAO();
        ResultDAO resultDAO = new ResultDAO();

        Exam exam = examDAO.getById(examId);
        List<Result> results = resultDAO.getResultsByExam(examId);

        double avgPercentage = resultDAO.getAveragePercentageByExam(examId);
        int passCount = resultDAO.getPassCountByExam(examId);
        int failCount = resultDAO.getFailCountByExam(examId);

        double highestScore = 0, lowestScore = 100;
        for (Result r : results) {
            if (r.getPercentage() > highestScore) highestScore = r.getPercentage();
            if (r.getPercentage() < lowestScore) lowestScore = r.getPercentage();
        }
        if (results.isEmpty()) { highestScore = 0; lowestScore = 0; }

        request.setAttribute("exam", exam);
        request.setAttribute("results", results);
        request.setAttribute("totalAttempts", results.size());
        request.setAttribute("averagePercentage", String.format("%.1f", avgPercentage));
        request.setAttribute("passCount", passCount);
        request.setAttribute("failCount", failCount);
        request.setAttribute("highestScore", String.format("%.1f", highestScore));
        request.setAttribute("lowestScore", String.format("%.1f", lowestScore));

        request.getRequestDispatcher("/WEB-INF/views/admin/exam-statistics.jsp").forward(request, response);
    }
}
