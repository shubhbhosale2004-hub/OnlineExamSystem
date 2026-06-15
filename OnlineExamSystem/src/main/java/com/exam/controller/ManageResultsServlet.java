package com.exam.controller;

import com.exam.dao.ExamDAO;
import com.exam.dao.ResultDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/results")
public class ManageResultsServlet extends HttpServlet {

    private final ResultDAO resultDAO = new ResultDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            resultDAO.deleteResult(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect(request.getContextPath() + "/admin/results?msg=deleted");
            return;
        }
        String examIdStr = request.getParameter("examId");
        if (examIdStr != null && !examIdStr.isEmpty()) {
            int examId = Integer.parseInt(examIdStr);
            request.setAttribute("results", resultDAO.getResultsByExam(examId));
            request.setAttribute("filterExamId", examId);
        } else {
            request.setAttribute("results", resultDAO.getAllResults());
        }
        request.setAttribute("exams", new ExamDAO().getAllExams());
        request.setAttribute("msg", request.getParameter("msg"));
        request.getRequestDispatcher("/WEB-INF/views/admin/manage-results.jsp").forward(request, response);
    }
}
