package com.exam.controller;

import com.exam.dao.AdminDAO;
import com.exam.dao.StudentDAO;
import com.exam.model.Admin;
import com.exam.model.Student;
import com.exam.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handles user login for both admin and student roles.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final AdminDAO adminDAO = new AdminDAO();
    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // If already logged in, redirect to appropriate dashboard
        if (SessionUtil.isAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            return;
        }
        if (SessionUtil.isStudent(request)) {
            response.sendRedirect(request.getContextPath() + "/student/dashboard");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try {
            if ("admin".equals(role)) {
                Admin admin = adminDAO.authenticate(username, password);
                if (admin != null) {
                    SessionUtil.setUserSession(request, admin, "admin", admin.getAdminId());
                    response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                    return;
                }
            } else {
                Student student = studentDAO.authenticate(username, password);
                if (student != null) {
                    if (!student.getIsActive()) {
                        request.setAttribute("error", "Your account has been deactivated. Please contact the administrator.");
                        request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                        return;
                    }
                    SessionUtil.setUserSession(request, student, "student", student.getStudentId());
                    response.sendRedirect(request.getContextPath() + "/student/dashboard");
                    return;
                }
            }
            // Authentication failed
            request.setAttribute("error", "Invalid username or password. Please try again.");
            request.setAttribute("selectedRole", role);
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("error", "An unexpected error occurred. Please try again later.");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        }
    }
}
