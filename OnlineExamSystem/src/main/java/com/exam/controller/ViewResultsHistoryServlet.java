package com.exam.controller;

import com.exam.dao.ResultDAO;
import com.exam.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/results")
public class ViewResultsHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = SessionUtil.getUserId(request);
        ResultDAO resultDAO = new ResultDAO();
        request.setAttribute("results", resultDAO.getResultsByStudent(studentId));
        request.getRequestDispatcher("/WEB-INF/views/student/results-history.jsp").forward(request, response);
    }
}
