package com.exam.controller;

import com.exam.dao.*;
import com.exam.model.Result;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentDAO studentDAO = new StudentDAO();
        SubjectDAO subjectDAO = new SubjectDAO();
        ExamDAO examDAO = new ExamDAO();
        ResultDAO resultDAO = new ResultDAO();

        request.setAttribute("totalStudents", studentDAO.getTotalStudentCount());
        request.setAttribute("totalSubjects", subjectDAO.getTotalSubjectCount());
        request.setAttribute("totalExams", examDAO.getTotalExamCount());
        request.setAttribute("totalResults", resultDAO.getTotalResultCount());

        List<Result> allResults = resultDAO.getAllResults();
        List<Result> recentResults = allResults.size() > 10 ? allResults.subList(0, 10) : allResults;
        request.setAttribute("recentResults", recentResults);

        request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
    }
}
