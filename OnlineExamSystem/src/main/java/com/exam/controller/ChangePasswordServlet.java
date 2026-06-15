package com.exam.controller;

import com.exam.dao.StudentDAO;
import com.exam.model.Student;
import com.exam.util.PasswordUtil;
import com.exam.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/change-password")
public class ChangePasswordServlet extends HttpServlet {

    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/student/change-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = SessionUtil.getUserId(request);
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        Student student = studentDAO.getById(studentId);

        if (!PasswordUtil.checkPassword(currentPassword, student.getPassword())) {
            request.setAttribute("error", "Current password is incorrect.");
        } else if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "New password and confirmation do not match.");
        } else if (newPassword.length() < 6) {
            request.setAttribute("error", "New password must be at least 6 characters long.");
        } else {
            if (studentDAO.changePassword(studentId, newPassword)) {
                request.setAttribute("success", "Password changed successfully.");
            } else {
                request.setAttribute("error", "Failed to change password. Please try again.");
            }
        }
        request.getRequestDispatcher("/WEB-INF/views/student/change-password.jsp").forward(request, response);
    }
}
