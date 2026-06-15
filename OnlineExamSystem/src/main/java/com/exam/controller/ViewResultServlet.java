package com.exam.controller;

import com.exam.dao.ResultDAO;
import com.exam.model.Result;
import com.exam.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/result")
public class ViewResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int attemptId = Integer.parseInt(request.getParameter("attemptId"));
            ResultDAO resultDAO = new ResultDAO();
            Result result = resultDAO.getByAttemptId(attemptId);

            if (result == null || result.getStudentId() != SessionUtil.getUserId(request)) {
                response.sendRedirect(request.getContextPath() + "/student/dashboard");
                return;
            }
            request.setAttribute("result", result);
            request.getRequestDispatcher("/WEB-INF/views/student/result.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/student/dashboard");
        }
    }
}
